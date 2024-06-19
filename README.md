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

**Solution Premise:** By making the data needed by the eCommerce interface readily available, we can improve its performance. We will explore various techniques to make the data reliable and readily available to eCommerce.

**Tech Stack**
1. Java
2. Postgres as DB
3. Hazelcast as cache provider

**Pre-Requisties**
1. Running Postgres 14
2. Running Hazelcast server
3. gradle
4. JDK 17

**Approach 1 Design**
 
![NIQ](https://github.com/durairajsekar/hazelcast/assets/147389197/8a653d2d-bfdd-4826-aec7-da1790318289)

**Approach 1 Premise**: While Ecommerce make API call to external-interface to retrieve the data from PDS, Resource are utilized for three operations. 
 1. Making an API call to PDS with request payload.
 2. Querying the PDS database with request payload.
 3. Transforming the result and filtering using request payload.

If we could optimize on the querying part we would be able to save resource and improve the performance of the API call. In this approach we can make use of hazelcast cache to stored the transformed result while data is stored in PDS, so that data needed for ECommerece would be readily available avoiding the DB query.

    
**Approach 1:** _Personalized Data Service registers with Hazelcast to store cache._
1. **Data Origin**: Data team sends the shopper and product details to Personalized Data Service (PDS).
2. **Data Persitence**: PDS persists the data into Postgres.
3. **Cache Update**: Upon saving into DB, details will be inserted into Hazelcast cache.
4. **Data Retrieval**: ECommmerce looks for shopper product details from PDS.
5. **Cache Usage**: ECommerce makes use of Hazelcast cache to retireve the data using key shopperId.

**Benefits of Approach 1:**
1. ECommerce makes API call to PDS but doesn't interact with DB to retrieve the shopper product details in PDS.
2. Hazelcast provides IMap which is based on HashMap datastructure optimized for faster retrieval of data using key.
3. Avoiding a DB call, which could be the bottleneck for ECommerce would significantly improve the performance of the API.

**Approach 2 Design**
![NIQ_2](https://github.com/durairajsekar/hazelcast/assets/147389197/214079a8-177e-4562-ac31-9fbe418ebd2b)

**Approach 2 Premise**: While Ecommerce make API call to external-interface to retrieve the data from PDS, Resource are utilized for three operations and in Approach 1 we optimized the DB query and we have two elements to look for optimization 
 1. Making an API call to PDS with request payload.
 3. Transforming the result and filtering using request payload.

If we could optimize on the API  part we would be able to avoid interacting with PDS thus saving resources of ECommerce and PDS as well.  In this approach we can make use of hazelcast cache cluster to stored the transformed result while data is stored in PDS and share it with ECommerce, so that data needed for ECommerece would be readily available without interacting with PDS.

**Approach 2:** _Personalized Data Service and ECommerce joins Hazelcast cluster to share cache._
1. **Data Origin**: Data team sends the shopper and product details to Personalized Data Service (PDS).
2. **Data Persitence**: PDS persists the data into Postgres.
3. **Cache Update**: Upon saving into DB, details will be inserted into Hazelcast cache in the shared cluster.
4. **Data Retrieval**: ECommmerce looks for shopper product details from the shared cluster of Hazelcast cache
5. **Cache Usage**: ECommerce makes use of Hazelcast cache to retireve the data using key shopperId.

**Benefits of Approach 2:** 
1. ECommerce doesn't interact with PDS to retrieve the shopper product details in PDS.
2. We are saving both API call to PDS and DB call to PDS DB.
3. ECommerce would be able to save resources and become highly available.

