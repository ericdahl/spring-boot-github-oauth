[![Build Status](https://travis-ci.org/ericdahl/spring-boot-github-oauth.svg)](https://travis-ci.org/ericdahl/spring-boot-github-oauth)

Example app showing using 3-legged OAuth2 to retrieve GitHub user emails. Note: this uses plain RestTemplate to make HTTP calls rather than a OAuth library.

#### Quick Start
##### Create a GitHub app
See https://developer.github.com/guides/basics-of-authentication/
##### Build

```$ mvn package```


##### Run
It's necessary to provide the client id and secret here. These can be specified in ```application.properties``` or on
the command line as arguments or as environment variables, etc.


```$ java -jar target/*jar --api.client_id=foo --api.client_secret=bar```


##### Use
- Load http://localhost:8080 and click "authorize"
- You should be redirected to a GitHub authentication page asking for access to read your email addresses
- Click Ok
- The app will then list your registered email addresses.
