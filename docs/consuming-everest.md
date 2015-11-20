#Consuming Everest
This document explains how to consume the Everest web service. It is assumed that Everest has already been installed, is currently running, and is available for consumption. If this is not case, please read [Deploying Everest](deploying-everest.md).  

Everest is a HATEOAS compliant RESTful webservice.  To properly consume Everest, it is important to understand the concepts behind HATEOAS. A wikipedia definition is given below:  

> HATEOAS, an abbreviation for Hypermedia as the Engine of Application State, is a constraint of the REST application architecture that distinguishes it from most other network application architectures. The principle is that a client interacts with a network application entirely through hypermedia provided dynamically by application servers. A REST client needs no prior knowledge about how to interact with any particular application or server beyond a generic understanding of hypermedia.

##Base Endpoint
Assuming the application is hosted at _HOST\_NAME_, the application should be accessible at http://_HOST\_NAME_/api.

##Security Token
In order to view content, Everest requires authentication in the form of an HTTP Header token. Tokens will likely be managed by the Everest server administrator.  

Once a token has been provided, the token may be passed under the HTTP Header: X\_AUTH\_TOKEN.