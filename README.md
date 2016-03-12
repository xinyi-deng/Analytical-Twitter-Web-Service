# TWITTER-ANALYTICS-WEB-SERVICE-Java-
The main idea of this project is to respond incoming queries with given twitter data. This project is done by a group of three. Code shown here deals with data ETL and mysql connection. Sever and HBase database building is not included.

#Functional Requirment:
1. Read JSON formatted data
2. Deal with real-life huge and dirty data
3. MySQL database schema design

#Functional Implementation:
1. Read JSON formatted data.
  JSON formatted data could be read in to java files with the help of external library 'gson'. Several additional classes has been         created to carry data. For detailed usage of 'gson' could be found on google.

2. Deal with real-life huge and dirty data
Data given is about one terabytes. The data set is huge. And it is real-life data with mul-formatted or incomplete data in it.

  For mul-formatted data, since we are interested in only parts of the data, if information we are looking for is complete, it will be     read in. However, if it is missing, it is simply ignored. I used try-catch blocks to surround parsing parts. If an error is caught, it   will not throw exception. A 'continue' is called, which simply moves on to the next record.

  Considering the size of data, it could be a disaster if it is run on a single computer. It has to be run parallelly. A map-reduce run   on AWS EMR platform if of great help. So all the data extraction mentioned above is done within mapper or reducer function, and is run   on distributed system.

3. MySQL database schema design
  Each query asks for different information, so a different table could be used for each query. Several optimization is done during the    design. For example, compare to 'char','int' is more space saving. So when ever posible, change 'char' to 'int'. And if we search on     several column, we could combine them into a single column to improve efficiency.

  Data to be loaded into MySQL is still large, dozens of gigabytes. So one way is to do data partition. This way we could make each table   smaller. I partition the whole data based on timestamp.

Besides, specific optimization is done for different queries.

#Query type
Query2: Text Cleaning and Analysis
The query asks for the tweet(s) posted by a given user at a specific time. And server response with: a. The tweet ID b. The censored text c. The sentiment score of that tweet

Query3:
The query asks for n most emotional posts of a user. A date range, a userid, and a number n is given, and sever response n tweets by the user that had the most positive and negative impact, based on the tweet impact score.


Query4: 
This query asks for the information about a given hashtag. A hashtag and a number n is given, and  service needs to return the top n sorted dates when the given hashtag is used including the following information per date: Data, Count, List of user ids and Source tweet of sent hashtag.

Query5:
The query asks for the total number of tweets sent by a range of userids. Two user ids are given and web service needs to return number of tweets sent within the range of user ids 





