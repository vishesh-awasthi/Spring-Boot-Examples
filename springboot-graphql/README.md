A simple spring boot project with graphQl implementation. 

#### Why GraphQL over Rest

**Network Performance**: When UI invokes a REST API, it will not have any control over the response data, whereas GraphQL provides a mechanism to specify the fields of interest in response. This reduces network overload by fetching the smallest data possible.

**Endpoint**: In REST, each resource is identified by a URI. This makes the client obliged to know each endpoint. In GraphQL, all resources are identified by a single endpoint. There is no hassle of maintaining multiple URI’s.

**Data Fetching Strategy**: In GraphQL, we have only one endpoint. The client sends a single query with the required fields. This helps with improved network performance and avoids the over-fetching/under-fetching data problem.



#### **Limitations**

GraphQL is indeed a great way to build and query APIs, but it does have certain limitations. A few of them are: 

The nested queries having multiple fields could lead to performance issues. GraphQL queries has to be carefully designed as the control is with client and it could ask anything.

Web caching is easier with REST compared to GraphQL, as the latter has a single endpoint.

Retrieving objects recursively (to infinite length) is not supported in GraphQL. One has to specify to what depth it needs the data to get the recursive data.