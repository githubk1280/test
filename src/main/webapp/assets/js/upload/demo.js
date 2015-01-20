$(function() {

    var output_format = "jpg";
    var loaded = 0;

    console.log = function(message) {
        console_out.innerHTML += message + '<br />';
        console_out.scrollTop = console_out.scrollHeight;
    }

    var fileInput = document.getElementById('doc-ipt-file-1');
    var progress = document.getElementById('Progress');
    var me = this;
    fileInput
        .addEventListener(
            'change',
            function(e) {
            	progress.style.display="block";
                var file = e.target.files[0];
                var size = file.size;
                if (window.FileReader) {
                    var fr = new FileReader();
                    fr.onloadend = function(e) {
                        loaded = 0;
                        var rsimage = document
                            .getElementById("source_image");

                        rsimage.src = e.target.result;
                        rsimage.style.display = "none";

                        // ----------
                        var source_image = document
                            .getElementById('source_image');

                        var result_image = document
                            .getElementById('result_image');

                        if (source_image.src == "") {
                            alert("You must load an image first!");
                            return false;

                        }
                        var quality = 15;

                        result_image.src = jic.compress(source_image,
                            quality, output_format).src;

                        result_image.onload = function() {
                            var image_width = $(result_image).width(),
                                image_height = $(
                                    result_image).height();
									// result_image.style.width = "95%";
									// result_image.style.height = "300px";
                            result_image.style.display = "block";

                        };
                        progress.style.display="none";
                    };
                    fr.onprogress = function(e) {
                        loaded += e.loaded;
                        progress.value = (loaded / size) * 100;

                    };
                    fr.readAsDataURL(file);

                }
            });

    // HANDLE UPLOAD BUTTON
    var uploadButton = document.getElementById("upload_button");
    uploadButton.addEventListener('click', function(e) {
        var result_image = document.getElementById('result_image');
        if (result_image.src == "") {
            alert("You must load an image and compress it first!");
            return false;
        }
        var callback = function(response) {

            alert("上传成功");
        }

        jic.upload(result_image, 'push', 'file', 'new.' + output_format,
            callback);

    }, false);

    /** * END OF DRAG & DROP STUFF WITH FILE API * */

});