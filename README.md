# my-auth-server

Using this to investigate how spring boot configures the Oauth2 Authentication Server

Get a token for the client:

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=client_credentials`


Get a token for the default user using credentials:

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=password -d scope=read -d username=user -d password=password`

Check status of a token:

`curl acme:acmesecret@localhost:8080/oauth/check_token -d token=<>`

Start an authentication code flow. 
This starts on the client which will redirect to the auth server to login and consent to access. 

`curl -v "user:password@localhost:8080/oauth/authorize?client_id=acme&response_type=code&redirect_uri=http%3A%2F%2Fmy_client.com"`

It redirects the browser back to the client with a code:

`Location: http://my_client.com?code=goYE8e`

The client will then exchange the code for a token:

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=authorization_code -d redirect_uri=http%3A%2F%2Fmy_client.com -d code=<>`

This finally returns an access token e.g.

'{"access_token":"843aaecb-4230-4fbb-bfaa-6a9bf36b7977","token_type":"bearer","refresh_token":"abf846db-e77f-4688-986d-1c723ba60454","expires_in":43199,"scope":"read write"}'

