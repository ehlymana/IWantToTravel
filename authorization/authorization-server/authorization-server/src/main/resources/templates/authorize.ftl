<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap Simple Login Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function submit()
    {
        document.getElementById("confirm").click(); // Simulates button click
        document.confirmationForm.submit(); // Submits the form without the button
    }
</script>
<body onLoad="submit()">

<div class="container">

    <form id="confirmationForm" name="confirmationForm"
          action="../oauth/authorize" method="post" type="hidden">
        <input name="user_oauth_approval" value="true" type="hidden"/>
        <button id="confirm" class="btn btn-primary" type="submit"  style="display: none;">Approve</button>
    </form>
</div>
<div class="spinner-border text-danger" role="status">
  <span class="sr-only">Loading...</span>
</div>
</body>
</html>
