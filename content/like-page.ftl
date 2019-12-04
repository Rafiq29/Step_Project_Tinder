<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    ${font}

    <!-- Bootstrap core CSS -->
    ${boot}

    <!-- Custom styles for this template -->
    ${style}
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-12 col-lg-12 col-md-12 text-center">
                    <img src=${imgURL} alt="" class="mx-auto rounded-circle img-fluid">
                    <h3 class="mb-0 text-truncated">${username}</h3>
                    <br>
                </div>
                <div class="col-12 col-lg-6">
                    <button type="button" class="btn btn-outline-danger btn-block"><span class="fa fa-times"></span> Dislike</button>
                </div>
                <div class="col-12 col-lg-6">
                    <button class="btn btn-outline-success btn-block"><span class="fa fa-heart"></span> Like</button>
                </div>
                <!--/col-->
            </div>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>
</div>

</body>
</html>
