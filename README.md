# jwd-crud-starter

Simple fullstack project that uses JWT authentication and authorization via self-signed JWTs.
Intended to be used as a quickstart project to allow for RBAC via Spring Boot and Angular.

## Why should I use this template?

This solution is different than most JWT examples you can find on Google/YouTube. Instead of rewriting everything, e.g.
the "OncePerRequestFilter" you see in many
examples, Spring Security is configured in such a way that the token is automatically checked.
That is, Spring already comes with such a "OncePerRequestFilter", namely "BearerTokenAuthenticationFilter", which you
can check in debugging mode to understand what is happening.

That means that the signature, the claims (e.g. roles), expiry, and many more things, are checked and a corresponding
HTTP error code is sent back automatically by Spring.
For example, if the roles are not sufficient, Spring is smart enough to send back a `403` error.
Similarly, if the token was altered during the transport (the signature is invalid) or the token is expired, Spring
sends back a `401` error.
Also, if a user tries to log in with bad credentials (wrong username/password, or username does not exist), Spring again
automatically sends a `401` error.

## What to do before running

The signing process for the JWTs uses asymmetric encryption. Therefore, create a public key and a private key under
`backend/src/main/resources/pki`.

Following commands might be helpful:

- Create a public/private keypair: `openssl genrsa -out keypair.pem 4096`
- Extract the public key: `openssl rsa -in keypair.pem -pubout -out public.pem`
- Extract the private key: `openssl pksc8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem`

### Test users

To create two test users, one being a user with role `ADMIN` and the other one with role `USER`, make sure to run the
application with the `datagen` profile. For more details, have a look at the `datagenerator` package in the `backend`.

## Misc

Big thanks to `@danvega` for letting us all know that Spring Security already has built-in support for JWTs.
This starter is based on [his template](https://github.com/danvega/jwt-username-password),
but he also has a great YouTube tutorial regarding this matter.

## Warnings, risks and closing remarks

I hope that my adoption of JWT gains popularity in small to mid-sized projects that need a simple role-based
authentication mechanism.
This template is especially helpful if you are planning on applying a monolithic business application, with just couple
thousand of users.
If you plan on shipping your software on a larger scale, please follow a gateway/microservice architecture and DO NOT
implement
security yourself, use something like Keycloak, which integrates well into Spring.

This authentication solution is only good for SIMPLE requirements which only need roles and a login. The moment you want
to introduce things like

- remember me
- blocking users after n invalid login tries
- forgot password, "send me an email"
- multifactorial authentication
- basically anything that is remotely stateful...

you would be much better off using finished and tested solutions. **I can't emphasize Keycloak enough in such cases!**
