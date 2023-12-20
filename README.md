## WordCouner

 Word frequency counter with Java8, Mockito, JUnit, Spring Boot and Maven

<h4>WordCounter</h4>

 * TDD: Basic intial tests created using Mockito and JUnit, Simple integration test with MockMvc
 * Two implementations tested initially: concurrentHashMap and Trie (with concurrentHashMap children nodes)
    * Opted for concurrentHashMap as performance (tested with 600k words added) difference is minimal, 
    * Trie will have higher overhead long term (and more complex to maintain), and since we dont need prefix/auto complete as only insertion and frequency count is required, direct concurrentHashMap is best.

 * concurrentHashMap is optimal for storing and counting words as it provides thread-safe concurrent operations, O(1) for insertion and lookup. It is also memory efficent and scalable.
 * AtomicInteger used for keeping count in the concurrentHashMap, ensuring atomic increments (thread-safe, prevents race conditions)
 * Utilized Fork/Join framework for parallel processing of word addition, in cases of large input
 * Adhered to Single Responsibility, Dependency Inversion and Interface Segregation Principles.

<h4>Microservice</h4>

* Spring Boot to develop and expose RESTful service for adding words and retrieving word counts
* Two Endpoints
  
### 1. Add Words
    Endpoint: `POST /api/v1/wordcounter/add`
    This endpoint allows clients to add words to the word counter. Words are processed and their occurrences are counted. Clients should send a JSON payload with the following structure:
    Example Input: { "text": "Sample text" }
    Example Output: "Words added successfully"

### 2. Get Word Count
    Endpoint: `GET /api/v1/wordcounter/count`
    Retrieves the count of a specific word that has been added to the counter. Word for which the count is required should be provided as a query parameter:
    Example Input: /api/v1/wordcounter/count?word=example
    Example Output: { "word": "text", "count": 1 }






 
