# message-processor
A message processing application that processes sales notificaation messages.

An input file in the form of a text file has been created in the resources. The messages are listed in it.
A Reader class parses the file and stores the messages as a list of Objects. 
A Message Class then randomly sends the messages to the Main function emulating an external message sending interface.
The SaleProcessor class carries out the processing functions like calculating the no of sales of each product 
and the adjustments made to each sale.
