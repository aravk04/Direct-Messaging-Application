Phase 3 implementing the GUI

Client GUI Class
Developed a ClientGUI which follows the Client class code. Allows the user to create a new account or log into their previous account. Implemented functionality to edit user information, message other users, and search other users. All inputs are sent to the client class which then sends it to the server. To run the class, run the Server Class and then run the ClientGUI class which creates an instance of the client to connect to the server. The ClientGUI will then open a window asking the user to either sign up or log in, after either of this actions the user will be presented with the main menu GUI which will access all other functionalities with buttons. Our code utilized Java swing features, a GUI toolkit for Java. In order to allow the server to run 2.csv files are required to create every messaging chat and to format the chat names, these are chats.csv and defaultFriend1-defaultFriend2.csv and are included in the repository. One .txt is also required to make sure new users are properly written to the database called input.txt, also included in the repository. No new methods are created in the ClientGUI class and it is run using the .run function from Runnable to allow concurrency in our project. To make sure our ClientGUI class worked we tested every possible input into text fields and implemented error handling for certain edge cases. Our code utilized JPanels when any significant error that wasn’t predicted such as password length or user messaging was encountered. This was followed up with a short JPanel Error showcasing the error to the user, then they are allowed to continue. 

Phase 2 implementing Server and Client classes

Client Class
Establishes connection by crreating socket connection to server it uses "localhost" as the address and "12345" as the port number. Then it chekcs user input and sends it to the server for it to process and do whatever command the user tells it to do. It keeps running until the client stops it and allows multiple clients in a server. The client class is one big menu that allows the user to choose what they want to do using a loop with if-else statements and more loops that take in user inputs and send them to the server class. For example if they want to sign up, log in, add or block friends, remove or unblock friends, and being able to send and delete messages. There are also other command sthe user can choose to do. This client class also handles invalid inputs by throwing exceptions and allowing the user to try again.

Client Interface
This is where the client class is run through 

Server Class
Looks for incoming client connections using server socket then accepts the connection and creates new client handler to communicate with the client and manages there requests. The client handler allows for it to work with multiple clients at once. Maintains data structures to store information about the users and interactions with the server. Uses multi-threading so that it can work with multiple clients without blocking.

Server class Methods: All of the following methods are private. createUser(String payload) creates new user account and extracts information. login(String payload) checks if username exists and if the password matches it. sendMessage(String payload) send message to receiver and creates a chat also checks and makes sure they are not blocked. viewChats(String payload) gets username and checks which chats the user is in. sendMessageInChat(String payload) sends message within a chat. deleteMessage(String payload) deletes username in specific chat and check username and line number of message to delete. addFriend(String payload) add friend to the user friend list if they are friends. removeFriend(String payload) remove friend from user list if they exist. blockUser(String payload) block user parses through the usernames and checks if they exist and adds them to the blocked list. unblockUser(String payload) unblocks user and removes them from the blocked list if they are there. deleteUser(String payload) parses usernames and deletes the user and any chats they were part of. createChat(String user1, String user2) creates unique chat between users. getChatIdForUser(String username) iterates through the chats to find the chat with the given username. writeToChat(String chatId, String message) writes a message and adds it to the chat if it exists.

Server Interface
This is where the server class is run through


Phase 1 implementing the Database

For this phase of the Team Project, create 5 classes total to serve as the basis of your Social Media Platform. The classes are as follows: BadInputException Is thrown whenever the input data is incorrect either due to parse error or just incorrect format

User The User class stores data about the user and allows for the user data to be interacted with

Database The Database class extracts data from a correctly formatted file and uses it to create an Arraylist of Users and allows for the interaction between them Following this it stores the data of the Users to a file for future use

Message The Message class stores the sender and receiver of the message in order to find its destination as well as the timestamp to decide the order in which messages are displayed

MessageDatabase The MessageDatabase class sorts messages and creates a file for each direct message between users in order to store the conversations for future access

Furthermore, you must create an interface for each class that acts as the skeleton for your classes. To specify what each class does observe below: BadInputException Constructor: public BadInputException(String message) calls the super constructor with the message

User Variables(All private): String name stores the real name of the user

String username stores the unique username of the user you must check if the username is valid before it enters the constructor

String emailAddress stores the email of the user you must ensure the email is valid

String password stores the password of the user

ArrayList friends stores all the users that this user is friends with

ArrayList blocked stores all the users that this user blocked

Constructors: public User(String name, String username, String password, String emailAddress) takes the specified variables above and assigns them to their respective variable

Methods(All public): get Methods create a get method for every variable listed above with the respective return type

set Methods create a set method for only the name, username, and password variables with the String Parameter

boolean addFriend(User u) adds a non-null User to the friends list if they are not in it returns true if the user was added

boolean removeFriend(User u) checks if the input user is in the friends list and removes them if found returns true if the user is removed

boolean blockUser(User u) first checks if the user is a friend and removes them from the friend list if they are next it adds the user into the blocked list returns true if the user was added

boolean unblockUser(User u) checks if the input user is in the blocked list and removes them if found returns true if the user is removed

boolean equals(Object o) determines if the name, username, email, and password are the same between this User and the input User

void sendMessage(Message m) creates a filename consisting of the sender's username followed by the receiver's or receivers' username(s) checks if a file by this name already exists if it does, creates a MessageDatabase object and calls addMessage to enter the sender's message onto the already existing file if it does not, creates the file and then creates, creates a MessageDatabase object and calls addMessage to enter the sender's message onto the created file

String toString() Returns the String in the form: name,friends,blocked,password,email,username Friends and Blocked will be each Users username separated by semicolons

Message Variables(All private):

String content stores the contents of the message

String sender stores the username of the sender

Arraylist receiver stores the username(s) of the receiver

String timeStamp stores the time as a String for later GUI implementation

long exactTime stores the time as a number to differentiate the order of the display of messages

Constructor: public Message(String sender, String receiver, String content) Creates the message with the above parameters

Methods(All public):

get Methods create a get method for each of the variables above with their respective return type

boolean sameDM(Message m) checks if the input message is part of the same DM as this messages by checking if both the sender and receiver of the input message match with the sender either the sender or receiver of this message s1 == s2 or s1 == r2 r1 == s2 or r1 == r2

MessageDatabase

Constructor: empty constructor

Method(public): boolean addMessage(String fileName, Message m) writes message, timestampt, sender, and receiver(s) onto file

Then there are the Test classes for each class to test and see if the methods are working as intended We have 4 test classes:

UserTest.java

testConstructorWithValidInputs: Checks if constructor in User class if it initializes a user object with valid input parameters

testAddFriend: Checks addFriend method and user can add another user as a friend without errors

testBlockUser: Checks blockUser method and if you can block a user, and it adds them to the blocked list

testRemoveFriend: Checks removeFriend method and when they remove a friend it removes them from the friend list

testEquals: Checks equals method and sees if two users are the same by checking name, email, address, username, and password

testConstructorWithInvalidUsername: Checks whether the username field is empty

testConstructorWithInvalidPassword: Checks if password is less than 8 characters if it is it throws an exception

testUnblockUser: First block user then checks if user is blocked then unblocks the user and checks if they are no longer on the unblocked list

testSendMessage: Creates sample data then calls method then deletes that from the file making sure it works properly

MessageTest.java

testMessageConstructor: This test checks if the constructor in the message class properly initializes a message object with valid parameters, and it is created without error

testSameDM: Checks sameDM method and if two messages are part of the same conversation by checking if the message has the same sender and receiver or different sender and receiver

testToString: Checks toString method and the information is in the right order and formatted properly

DatabaseTest.java

testConstructorWithValidInputs: This test check if the constructor in the database class properly initializes a database object with valid parameters, and it is created without error

testAddUser: This test check if the addUser in the database class properly add a user to the input.txt with valid information

testRemoveUser: This test check if the removeUser in the database class properly remove a user to the input.txt

testEditUser: This test check if the editUser in the database class preperly works

MessageDatabaseTest.java

testAddMessage: tests if the method add message to file correctly


There are 4 interfaces as well one for each class 

MessageInterface:
This interface specifies methods that the Message class should use which are getSender, getReceivers, getContent, getTimestamp, getExactTime, sameDM(MessageInterface m), and a toString method

MessageData:
This interface specifies methods that the MessageDatabase class should use which includes boolean addMessage(String fileName, Message m) throws FileNotFoundException, IOException

UserInterface:
This interface specifies methods that User class should use which include the addFriend(User u), blockUser(User u), removeFriend(User u), unblockUser(User u), getName, getFriends, getBlocked, getPassword, getEmailAddress, getUsername, setName(String name), setEmailAddress(String emailAddress), and setPassword(String password).

Data:
This interface specifies methods that the Database class should use which include void setUsers(ArrayList<User> users), void readFile() throws IncorrectInfoException, boolean searchUser(String username), boolean addUser(String info), String viewUser(String username), boolean editUser(String oldInfo, String newInfo), boolean removeUser(String username)

