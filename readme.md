The aim of this application is to explore a range of compression techniques.

Start the spring boot application with command:
> ./mvnw spring-boot:run

Once up and running, the following endpoints are now available to test out:
> tut/binary/encode 

Encodes a given payload in binary:

![binary](/src/main/resources/bin-encode.png)

> /tut/probability/distribution 

Gives back a probability distribution for a given payload.

![dist](/src/main/resources/probability-dist.png)

> /tut/huffman/tree 

Generates a binary huffman tree for a given payload.

![huffman_tree](/src/main/resources/huffman-tree.png)

> /tut/huffman/encode 

Generates huffman encoding for a given payload. Entropy: log2(n) where n is the length of the message.

![huffman_encode](/src/main/resources/huffman-encode.png)
> 
> /tut/shannon/entropy 

Calculates the entropy for a given message, e.g. the theoretical lossless compression limit.

![shannon](/src/main/resources/shannon-entropy.png)

> /tut/arithmetic/bounds

Calculates the numeric bounds within which a payload can be represented as a single numeric value, based on the probability distribution of a given payload.

![bounds](/src/main/resources/arith-bounds.png)


> /tut/arithmetic/encode
   
Computes a single numeric encoding for an entire payload. Ergo, a single VLC for the entire payload.

![arith_encode](/src/main/resources/arithmetic-encode.png)