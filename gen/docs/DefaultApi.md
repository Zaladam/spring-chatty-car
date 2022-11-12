# DefaultApi

All URIs are relative to *http://localhost:9006*

Method | HTTP request | Description
------------- | ------------- | -------------
[**tripsGet**](DefaultApi.md#tripsGet) | **GET** /trips | Get list of trips with optional search queries
[**tripsIdDelete**](DefaultApi.md#tripsIdDelete) | **DELETE** /trips/{id} | Delete trip
[**tripsIdGet**](DefaultApi.md#tripsIdGet) | **GET** /trips/{id} | Get trip information
[**tripsIdPassengersGet**](DefaultApi.md#tripsIdPassengersGet) | **GET** /trips/{id}/passengers | Get list of passengers of a trip, with pending, accepted and refused status
[**tripsPost**](DefaultApi.md#tripsPost) | **POST** /trips | Create a trip
[**tripsTripIdPassengersUserIdDelete**](DefaultApi.md#tripsTripIdPassengersUserIdDelete) | **DELETE** /trips/{trip_id}/passengers/{user_id} | Remove user from passengers of a trip
[**tripsTripIdPassengersUserIdGet**](DefaultApi.md#tripsTripIdPassengersUserIdGet) | **GET** /trips/{trip_id}/passengers/{user_id} | Get passenger status
[**tripsTripIdPassengersUserIdPost**](DefaultApi.md#tripsTripIdPassengersUserIdPost) | **POST** /trips/{trip_id}/passengers/{user_id} | Add user as passenger to a trip with pending status
[**tripsTripIdPassengersUserIdPut**](DefaultApi.md#tripsTripIdPassengersUserIdPut) | **PUT** /trips/{trip_id}/passengers/{user_id} | Update passenger status


<a name="tripsGet"></a>
# **tripsGet**
> List&lt;Trip&gt; tripsGet(originLon, departureDate, originLat, destinationLat, destinationLon)

Get list of trips with optional search queries

Limits list to 20 trips, ordered by latest created if not otherwise specified. Always filters out trips without any available seating left. Departure date query will filter only trips departing on the same date. Origin query (lat+lon) will order by distance with specified origin position. Destination query (lat+lon) will order by distance with specified destination position. Both origin and destination queries will order by sum of distances.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    BigDecimal originLon = new BigDecimal(78); // BigDecimal | Longitude of the starting position
    String departureDate = "2022-10-24"; // String | Date of departure
    BigDecimal originLat = new BigDecimal(78); // BigDecimal | Latitude of the origin position
    BigDecimal destinationLat = new BigDecimal(78); // BigDecimal | Latitude of the destination position
    BigDecimal destinationLon = new BigDecimal(78); // BigDecimal | Longitude of the destination position
    try {
      List<Trip> result = apiInstance.tripsGet(originLon, departureDate, originLat, destinationLat, destinationLon);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **originLon** | **BigDecimal**| Longitude of the starting position |
 **departureDate** | **String**| Date of departure | [optional]
 **originLat** | **BigDecimal**| Latitude of the origin position | [optional]
 **destinationLat** | **BigDecimal**| Latitude of the destination position | [optional]
 **destinationLon** | **BigDecimal**| Longitude of the destination position | [optional]

### Return type

[**List&lt;Trip&gt;**](Trip.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Both latitude and longitude should be specified for a position query |  -  |
**200** | Sent list of trips |  -  |

<a name="tripsIdDelete"></a>
# **tripsIdDelete**
> tripsIdDelete(id)

Delete trip

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer id = 38; // Integer | ID of the trip
    try {
      apiInstance.tripsIdDelete(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| ID of the trip |

### Return type

null (empty response body)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | No trip found with this ID |  -  |
**201** | Trip is deleted |  -  |

<a name="tripsIdGet"></a>
# **tripsIdGet**
> Trip tripsIdGet(id)

Get trip information

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer id = 38; // Integer | ID of the trip
    try {
      Trip result = apiInstance.tripsIdGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| ID of the trip |

### Return type

[**Trip**](Trip.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**404** | No trip found with this ID |  -  |
**200** | Sent trip information |  -  |

<a name="tripsIdPassengersGet"></a>
# **tripsIdPassengersGet**
> Passengers tripsIdPassengersGet(id)

Get list of passengers of a trip, with pending, accepted and refused status

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer id = 38; // Integer | ID of the trip
    try {
      Passengers result = apiInstance.tripsIdPassengersGet(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsIdPassengersGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**| ID of the trip |

### Return type

[**Passengers**](Passengers.md)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Status not in accepted values |  -  |
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | No trip found with this ID |  -  |
**200** | Sent list of passengers |  -  |

<a name="tripsPost"></a>
# **tripsPost**
> Trip tripsPost(newTrip)

Create a trip

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    NewTrip newTrip = new NewTrip(); // NewTrip | Information of the trip
    try {
      Trip result = apiInstance.tripsPost(newTrip);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **newTrip** | [**NewTrip**](NewTrip.md)| Information of the trip | [optional]

### Return type

[**Trip**](Trip.md)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Trip in request is not correct |  -  |
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**201** | Trip is created, sent trip created with its assigned ID |  -  |

<a name="tripsTripIdPassengersUserIdDelete"></a>
# **tripsTripIdPassengersUserIdDelete**
> tripsTripIdPassengersUserIdDelete(tripId, userId)

Remove user from passengers of a trip

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer tripId = 38; // Integer | ID of the trip
    Integer userId = 12; // Integer | ID of the user
    try {
      apiInstance.tripsTripIdPassengersUserIdDelete(tripId, userId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsTripIdPassengersUserIdDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tripId** | **Integer**| ID of the trip |
 **userId** | **Integer**| ID of the user |

### Return type

null (empty response body)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | User is not a passenger |  -  |
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | Trip or user not found |  -  |
**200** | Used removed from passenger |  -  |

<a name="tripsTripIdPassengersUserIdGet"></a>
# **tripsTripIdPassengersUserIdGet**
> String tripsTripIdPassengersUserIdGet(tripId, userId)

Get passenger status

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer tripId = 38; // Integer | ID of the trip
    Integer userId = 12; // Integer | ID of the user
    try {
      String result = apiInstance.tripsTripIdPassengersUserIdGet(tripId, userId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsTripIdPassengersUserIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tripId** | **Integer**| ID of the trip |
 **userId** | **Integer**| ID of the user |

### Return type

**String**

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | Trip or user not found |  -  |
**200** | Sent passenger status |  -  |

<a name="tripsTripIdPassengersUserIdPost"></a>
# **tripsTripIdPassengersUserIdPost**
> tripsTripIdPassengersUserIdPost(tripId, userId)

Add user as passenger to a trip with pending status

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer tripId = 38; // Integer | ID of the trip
    Integer userId = 12; // Integer | ID of the user
    try {
      apiInstance.tripsTripIdPassengersUserIdPost(tripId, userId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsTripIdPassengersUserIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tripId** | **Integer**| ID of the trip |
 **userId** | **Integer**| ID of the user |

### Return type

null (empty response body)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | User is already a passenger, or the ride has no available seating left |  -  |
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | Trip or user not found |  -  |
**201** | User added as pending passenger |  -  |

<a name="tripsTripIdPassengersUserIdPut"></a>
# **tripsTripIdPassengersUserIdPut**
> tripsTripIdPassengersUserIdPut(tripId, userId, status)

Update passenger status

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:9006");
    
    // Configure HTTP bearer authorization: authentication
    HttpBearerAuth authentication = (HttpBearerAuth) defaultClient.getAuthentication("authentication");
    authentication.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    Integer tripId = 38; // Integer | ID of the trip
    Integer userId = 12; // Integer | ID of the user
    String status = "status_example"; // String | New status of the passenger
    try {
      apiInstance.tripsTripIdPassengersUserIdPut(tripId, userId, status);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#tripsTripIdPassengersUserIdPut");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **tripId** | **Integer**| ID of the trip |
 **userId** | **Integer**| ID of the user |
 **status** | **String**| New status of the passenger | [enum: accepted, refused]

### Return type

null (empty response body)

### Authorization

[authentication](../README.md#authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | User is not a passenger, or is not in pending status, or status not in accepted value |  -  |
**401** | Missing authentification |  -  |
**403** | Not identified as the corresponding user |  -  |
**404** | Trip or user not found |  -  |
**200** | Status is updated |  -  |

