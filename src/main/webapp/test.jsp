<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Bootstrap styles -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<!-- blueimp Gallery styles -->
<link rel="stylesheet" href="plugins/blueimp-gallery/css/blueimp-gallery.min.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="plugins/jQuery-File-Upload-master/css/jquery.fileupload.css">
<link rel="stylesheet" href="plugins/jQuery-File-Upload-master/css/jquery.fileupload-ui.css">

<style>
/* Hide Angular JS elements before initializing */
.ng-cloak {
    display: none;
}

body {
  padding: 10px;
}
</style>

    <!-- The file upload form used as target for the file upload widget -->
    <form id="fileupload" action="" method="POST" enctype="multipart/form-data" data-ng-app="demo" data-ng-controller="DemoFileUploadController" data-file-upload="options">
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button" ng-class="{disabled: disabled}">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>选择文件...</span>
                    <input type="file" name="files[]" multiple ng-disabled="disabled">
                </span>
                <button type="button" class="btn btn-primary start" data-ng-click="submit()">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>开始上传</span>
                </button>
                <button type="button" class="btn btn-warning cancel" data-ng-click="cancel()">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消上传</span>
                </button>
                
                <button type="button" class="btn btn-warning cancel" data-ng-click="create()">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>提交</span>
                </button>
                
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fade" data-ng-class="{in: active()}">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" data-file-upload-progress="progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped files ng-cloak">
            <tr data-ng-repeat="file in queue" data-ng-class="{'processing': file.$processing()}">
                <td data-ng-switch data-on="!!file.thumbnailUrl">
                    <div class="preview" data-ng-switch-when="true">
                        <a data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery><img data-ng-src="{{file.thumbnailUrl}}" alt=""></a>
                    </div>
                    <div class="preview" data-ng-switch-default data-file-upload-preview="file"></div>
                </td>
                <td>
                    <p class="name" data-ng-switch data-on="!!file.url">
                        <span data-ng-switch-when="true" data-ng-switch data-on="!!file.thumbnailUrl">
                            <a data-ng-switch-when="true" data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}" data-gallery>{{file.name}}</a>
                            <a data-ng-switch-default data-ng-href="{{file.url}}" title="{{file.name}}" download="{{file.name}}">{{file.name}}</a>
                        </span>
                        <span data-ng-switch-default>{{file.name}}</span>
                    </p>
                    <strong data-ng-show="file.error" class="error text-danger">{{file.error}}</strong>
                </td>
                <td>
                    <p class="size">{{file.size | formatFileSize}}</p>
                    <div class="progress progress-striped active fade" data-ng-class="{pending: 'in'}[file.$state()]" data-file-upload-progress="file.$progress()"><div class="progress-bar progress-bar-success" data-ng-style="{width: num + '%'}"></div></div>
                </td>
                <td>
                    <button type="button" class="btn btn-primary start" data-ng-click="file.$submit()" data-ng-hide="!file.$submit || options.autoUpload" data-ng-disabled="file.$state() == 'pending' || file.$state() == 'rejected'">
                        <i class="glyphicon glyphicon-upload"></i>
                        <span>开始</span>
                    </button>
                    <button type="button" class="btn btn-warning cancel" data-ng-click="file.$cancel()" data-ng-hide="!file.$cancel">
                        <i class="glyphicon glyphicon-ban-circle"></i>
                        <span>取消</span>
                    </button>
                    <button data-ng-controller="FileDestroyController" type="button" class="btn btn-danger destroy" data-ng-click="file.$destroy()" data-ng-hide="!file.$destroy">
                        <i class="glyphicon glyphicon-trash"></i>
                        <span>删除</span>
                    </button>
                </td>
            </tr>
        </table>
    </form>


<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="plugins/angularJs/angular.min.js"></script>

<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="plugins/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="plugins/blueimp-gallery/js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="plugins/blueimp-gallery/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- blueimp Gallery script -->
<script src="plugins/blueimp-gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload Angular JS module -->
<script src="plugins/jQuery-File-Upload-master/js/jquery.fileupload-angular.js"></script>
<!-- The main application script -->
<script src="plugins/jQuery-File-Upload-master/js/app.js"></script>