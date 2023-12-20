## WordCouner

 Word frequency counter with Java8, Mockito, JUnit, Spring Boot and Maven

 <h4>Note:</h4>
 1. A dummy Translator service is added to make sure the microservice functions properly. Please use the Translator interface to add the actual Translator (Remove the dummy/ add @Primary to your implementation).
 <br />
 2. Trie implementation classes left in code just for reference and potential further improvements. (Not being used currently)

<h3>WordCounter</h3>

 * TDD: Basic intial tests, tests for LargeDataProcessing and concurrentWordAddition created using Mockito and JUnit, Simple integration test with MockMvc
 * Two implementation approaches tested initially: concurrentHashMap and Trie (with concurrentHashMap children nodes)
    * Opted for concurrentHashMap as performance (tested with 600k words added) difference is minimal, 
    * Trie will have higher overhead long term (and more complex to maintain), and since we dont need prefix/auto complete as only insertion and frequency count is required, direct concurrentHashMap is best.

 * concurrentHashMap is optimal for storing and counting words as it provides thread-safe concurrent operations, O(1) for insertion and lookup. It is also memory efficent and scalable.
 * AtomicInteger used for keeping count in the concurrentHashMap, ensuring atomic increments (thread-safe, prevents race conditions)
 * Utilized Fork/Join framework for parallel processing of word addition, in cases of large input
 * Adhered to Single Responsibility, Dependency Inversion and Interface Segregation Principles.
 * Basic Logging with Logger

<h3>Microservice</h3>

* Spring Boot to develop and expose RESTful service for adding words and retrieving word counts
* Basic Global Exception handling and validation with Spring Boot
* Two Endpoints
  
### 1. Add Words
    Endpoint: `POST /api/v1/wordcounter/add`
    This endpoint allows clients to add words to the word counter. Words are processed and their occurrences are counted. Clients should send a JSON payload with the following structure:
    Example Input: { "text": "Sample text" }
    Example Output: "Words added successfully"

### 2. Get Word Count
    Endpoint: `GET /api/v1/wordcounter/count`
    Retrieves the count of a specific word that has been added to the counter. Word for which the count is required should be provided as a query parameter:
    Example Input: /api/v1/wordcounter/count?word=text
    Example Output: { "word": "text", "count": 1 }

<h3>Running and Testing</h3>

 * Basic implementation, so import project as an exisiting Maven Project. JUnit Tests, Run WordcountApplication to view and use the api end points

<h3>Additional Info</h3>
 
 * The service can be hosted on providers like AWS, GCP (Google App Enginer) for easy deployment, scaling and managemet
 * Resiliency can be ensured through Load balancing, Redundancy (Reduce outage risks), Regular backups and implementing appropriate security measures.

