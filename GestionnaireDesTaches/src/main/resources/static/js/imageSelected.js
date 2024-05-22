$(function()
{
    $('#profilePicturesrc').change( function(event) {
        var tmppath = URL.createObjectURL(event.target.files[0]);
        $('.profilePicture').fadeIn("fast").attr('src',tmppath);
    });
});
// var outImage ="profilePicture";
// function preview_2(obj)
// {
//     var file = document.getElementById("profilePicturesrc");
//
//     var filepath = document.getElementById("profilePicturesrc").files[0];
//     for (var i = 0; i < filepath.files.length; i++) {
//         console.log(filepath.files[i].name);
//     }
//
//     // console.log("file : "+filepath.valueOf());
//     // console.log("file src : "+filepath.src);
//     // console.log("file type : "+filepath.type);
//     // console.log("file mozFullPath : "+files[0].mozFullPath);
//     // console.log("FileReader : "+FileReader);
//     // console.log("profilePicturesrc : "+document.getElementById("profilePicturesrc").files);
//     if (FileReader)
//     {
//         var reader = new FileReader();
//         reader.readAsDataURL(obj.files[0]);
//         reader.onload = function (e) {
//             var image=new Image();
//             image.src=e.target.result;
//             image.onload = function () {
//                 document.getElementById(outImage).src=image.src;
//             };
//         }
//     }
//     else
//     {
//         // Not supported
//     }
// }