For this phase of the Team Project, create 5 classes total to serve as the basis of your Social Media Platform. The classes are as follows:
BadInputException
Is thrown whenever the input data is incorrect either due to parse error or just incorrect format

User
The User class stores data about the user and allows for the user data to be interacted with

Database
The Database class extracts data from a correctly formatted file and uses it to create an Arraylist of Users and allows for the interaction between them
Following this it stores the data of the Users to a file for future use

Message
The Message class stores the sender and receiver of the message in order to find its destination as well as the timestamp to decide the order in which messages are displayed

MessageDatabase
The MessageDatabase class sorts messages and creates a file for each direct message between users in order to store the conversations for future access

Furthermore, you must create an interface for each class that acts as the skeleton for your classes. To specify what each class does observe below:
BadInputException
Constructor:
public BadInputException(String message)
calls the super constructor with the message

User
Variables(All private):
String name
stores the real name of the user

String username
stores the unique username of the user
you must check if the username is valid before it enters the constructor

String emailAddress
stores the email of the user
you must ensure the email is valid

String password
stores the password of the user

ArrayList<User> friends
stores all the users that this user is friends with

ArrayList<User> blocked
stores all the users that this user blocked

Constructors:
public User(String name, String username, String password, String emailAddress)
takes the specified variables above and assigns them to their respective variable

Methods(All public):
get Methods
create a get method for every variable listed above with the respective return type

set Methods
create a set method for only the name, username, and password variables with the String Parameter

boolean addFriend(User u)
adds a non-null User to the friends list if they are not in it
returns true if the user was added

boolean removeFriend(User u)
checks if the input user is in the friends list and removes them if found
returns true if the user is removed

boolean blockUser(User u)
first checks if the user is a friend and removes them from the friend list if they are
next it adds the user into the blocked list
returns true if the user was added

boolean unblockUser(User u)
checks if the input user is in the blocked list and removes them if found
returns true if the user is removed

boolean equals(Object o)
determines if the name, username, email, and password are the same between this User and the input User

String toString()
Returns the String in the form:
name,friends,blocked,password,email,username
Friends and Blocked will be each Users username separated by semicolons

Database
Message
Variables(All private):

String content
stores the contents of the message

String sender
stores the username of the sender

Arraylist<String> receiver
stores the username(s) of the receiver

String timeStamp
stores the time as a String for later GUI implementation

long exactTime
stores the time as a number to differentiate the order of the display of messages

Constructor:
public Message(String sender, String receiver, String content)
Creates the message with the above parameters

Methods(All public):

get Methods
create a get method for each of the variables above with their respective return type

boolean sameDM(Message m)
checks if the input message is part of the same DM as this messages by checking if both the sender and receiver of the input message match with the sender either the sender or receiver of this message
s1 == s2 or s1 == r2
r1 == s2 or r1 == r2

Then there are the Test classes for each class to test and see if the methods are working as intended
We have 4 test classes:

UserTest.java
testUserConstructor:
Checks if constructor in User class if it initializes a user object with valid input parameters
testAddFriend:
Checks addFriend method and user can add another user as a friend without errors
testBlockUser:
Checks blockUser method and if you can block a user, and it adds them to the blocked list
testRemoveFriend:
Checks removeFriend method and when they remove a friend it removes them from the friend list
testEqual: 
Checks equals method and sees if two users are the same by checking name, email, address, username, and password
testToString:
Checks toString method and makes sure it is the right information for the user object
testUpdateName:
Checks updateName method and if the username is updated when it has been changed
testSetEmailAddress:
Checks setEmailAddress  and makes sure it can be updated when it has been changed
testSetPassword:
Checks setPassword method and see if the user's password can be updated and properly set
testSetUsername:
Checks setUsername method and if the username can be updated and it is changed in the user object

MessageTest.java
testMessageConstructor:
This test checks if the constructor in the message class properly initializes a message object with valid parameters, and it is created without error
testSameDM:
Checks sameDM method and if two messages are part of the same conversation by checking if the message has the same sender and receiver or different sender and receiver
testToString:
Checks toString method and the information is in the right order and formatted properly

DatabaseTest.java
testReadFile:
Creates temporary file and sample data and checks if the file can be properly read, parsed, and stored in the database object
testWriteFile:
Checks if data can be written to a file from the database object