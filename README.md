# my-auth-server

Using this to investigate how spring boot configures the Oauth2 Authentication Server

Spring exposes the following URLs:

| Endpoint                  | Description                                               |
| ------------------------- | --------------------------------------------------------- |
| GET /oauth/authorize      | Ask for an authorisation code.                            |
| POST /oauth/authorize     | Web based consent posts here, otherwise a synonym for GET |
| GET /oauth/token          | Synonym for POST, disabled by default                     |
| POST /oauth/token         | Exchange an authorisation code for an access token        |
| GET /oauth/check_token    | Get the details of a token                                |
| GET /oauth/confirm_access | Presents web consent page                                 |
| GET /oauth/error          | ???                                                       |

---

### Start an authentication code flow 

This starts on the client which will redirect to the auth server (GET /oauth/authorize) to login and consent to access. 

`curl -v "user:password@localhost:8080/oauth/authorize?client_id=acme&response_type=code&redirect_uri=http%3A%2F%2Fmy_client.com"`

It redirects the browser back to the client with a code:

`Location: http://my_client.com?code=goYE8e`

The client will then exchange the code for a token (POST /oauth/token):

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=authorization_code -d redirect_uri=http%3A%2F%2Fmy_client.com -d code=<>`

This finally returns an access token e.g.

```json
{
     "access_token": "843aaecb-4230-4fbb-bfaa-6a9bf36b7977",
     "token_type": "bearer",
     "refresh_token": "abf846db-e77f-4688-986d-1c723ba60454",
     "expires_in": 43199,
     "scope": "read write"
}
 ```

---

### Alternate methods of getting and checking a token are:

##### Get a token for the client (POST /oauth/token):

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=client_credentials`


##### Get a token for the default user using credentials (POST /oauth/token):

`curl acme:acmesecret@localhost:8080/oauth/token -d grant_type=password -d scope=read -d username=user -d password=password`

##### Check status of a token (POST /oauth/check_token):

`curl acme:acmesecret@localhost:8080/oauth/check_token -d token=<>`

---

### Web based approval

Comment out the auto-approve-scopes and put the following url in a browser:

`localhost:8080/oauth/authorize?client_id=acme&response_type=code&redirect_uri=http%3A%2F%2Fmy_client.com`

Log in as user:password and you will see a confirmation screen asking you to confirm access (/oauth/confirm_access).

This will post back to /oauth/authorize which will redirect you to the redirect_uri with a code which can be exchanged for an access token as before.

If you do not grant access you wil be redirected to

`http://my_client.com/?error=access_denied&error_description=User%20denied%20access`


