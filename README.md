# my-auth-server

Using this to investigate how spring boot configures the Oauth2 Authentication Server

Get a token for the client:

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=client_credentials`


Get a token for the default user:

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=password -d scope=read -d username=user -d password=<see log>`


curl acme:acmesecret@localhost:8080/oauth/check_token -d token=<>
