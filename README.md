**Project Hazelcast**

Activate is an API service that provides shoppers
personalized information to eCommerce servers.
The service consists of 2 main parts:
1. Interface with data team (internal) - Receiving shoppers’ personalized
information and product metadata from our data team and storing it in a specified
database.
2. Interface with eCommerce (external) - Provide fast-reading operation for the
shoppers’ personalized information.
![image](https://github.com/durairajsekar/hazelcast/assets/147389197/0b192af7-9413-43ea-80db-5f6bf1ba0059)

**Problem Statement:** eCommerce interface is a real-time interface and requires high performance.

**Solution Premise:** By making the data needed by eCommerce interface readily available we could improve its performance. We will explore various techniuqe to make the data reaily available and reliabily closer to eCommerce.

**Tech Stack**
1. Java
2. Postgres as DB
3. Hazelcast as cache provider

**Pre-Requisties**
1. Running Postgres 14
2. Running Hazelcast server
3. gradle
4. JDK 17

**Solution Design**
 
![NIQ](https://github.com/durairajsekar/hazelcast/assets/147389197/8a653d2d-bfdd-4826-aec7-da1790318289)


**Approach 1:** _Personalized Data Service registers with Hazelcast to store cache._
1. Data team sends the shopper and product details to Personalized Data Service (PDS).
2. PDS persists the data into Postgres.
3. Upon saving into DB, details will be inserted into Hazelcast cache.
4. ECommmerce looks for shopper product details from PDS.
5. ECommerce makes use of Hazelcast cache to retireve the data using key shopperId.

**Benefits of Approach 1:**
1. ECommerce doesn't interact with DB to retrieve the shopper product details in PDS.
2. Hazelcast provides IMap which is based on HashMap datastructure optimized for faster retrieval of data using key.
3. Avoiding a DB call which could be the bottleneck for ECommerce would significantly improve the performance of the API.

**Approach 2:** _Personalized Data Service and ECommerce joins Hazelcast cluster to share cache._
1. Data team sends the shopper and product details to Personalized Data Service (PDS).
2. PDS persists the data into Postgres.
3. Upon saving into DB, details will be inserted into Hazelcast cache in the shared cluster.
4. ECommmerce looks for shopper product details from the shared cluster of Hazelcast cache
5. ECommerce makes use of Hazelcast cache to retireve the data using key shopperId.

**Benefits of Approach 2:** 
1. ECommerce doesn't interact with PDS to retrieve the shopper product details in PDS.
2. We are saving both API call to PDS and DB call to PDS DB.
3. ECommerce would be able to save resources and become highly available.


