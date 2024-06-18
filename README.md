**Project Hazelcast**

Activate is an API service that provides shoppers
personalized information to eCommerce servers.
The service consists of 2 main parts:
1. Interface with data team (internal) - Receiving shoppers’ personalized
information and product metadata from our data team and storing it in a specified
database.
2. Interface with eCommerce (external) - Provide fast-reading operation for the
shoppers’ personalized information.

**Design**
 
![NIQ](https://github.com/durairajsekar/hazelcast/assets/147389197/8a653d2d-bfdd-4826-aec7-da1790318289)

**Tech Stack**
1. Java
2. Postgres as DB
3. Hazelcast as cache provider

**Pre-Requisties**
1. Running Postgres 14
2. Running Hazelcast server
3. gradle
4. JDK 17
