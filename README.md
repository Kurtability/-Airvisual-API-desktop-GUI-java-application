# SCD2_2021_Exam
Applied for special consideration. New deadline shall be uploaded
here once I receive the response.

Level of implemented features so far:
pass

Structure of the config file:
IQAIR_API_Key: This is where the user enters his/her IQAIR api key
TWILLO_API_SID: This is where the user enters his/her TWILLO api sid
TWILLO_API_AuthToken: This is where the user enters his/her TWILLO api authtoken
TWILLO_API_FromTwilloPhoneNumber: This is where the user enters his/her TWILLO number that was generated from the free trial 
TWILLO_API_ToMyPhoneNUmber: This is where the user enters his/her receiving number, however, the phone number has te be verified by TWILLO first


reference of HTTP post/get 
https://openjdk.java.net/groups/net/httpclient/intro.html where I got the sample of HTTP post/get request



Quirks of the program:
The output api might send report to the receiver's phone twice. Might
be due reasons from the carrier's end. Already made sure that the 
program sendspost request only once.

TDD red-green-refactor process:
first input api function
red: 4a1c405dd613bff47c14615d5ec04ee0e82092eb
green: 9d71a36cc89ebd985987351787c05e38e9c047cb

Second input api function
red: 8207558f7da2daf1e0c03eda3a365b75a53d545d
green: 098ec7aa8449fe84310f717797b88e47c0fe771a

third input api function
red: 48bb3912b9a82d9806e816d0ff228772ca616746
green: c10867e614f2a9bd0f4d85f6a7882926964569a4

fourth input api function
red: b0d43b23851edef7ae7f15e3164c636a0fc4eefe
green: e50889fa1b94d627f3d8b4b6e30f76af99e49156

json 1st function
red: ecfb7218b98005efb16c4bdf9ec0a40bffe59645
green: b9ee2b69ae832e7de8ebe19e52bc73701bd48433

json 2nd function
red: 80644e5759523c7023bfe5975f6ebba21485c595
green:6c21fae092e55460d157ead0a0ee18b21feb69b1

json 3rd function 
red: ae45c3e6fc1f3cdf2d1fc175bbc3826fa0f26870
green:dc713ab87557f397a2114aef40f43116fd9c9fca

json 4th function
red: 63849d6c0d324d20fba5e4c176ddff58fcb77910
green: 51f082bae8fcd57925d236d0c22a4f649fb56bca

1st refactor: implemented the input online/offline instantiation from main App class
2eed94751550738efb800e82d7c530c1fa0a489d

2nd refactor: the parser now will handle errors from API, which will stop the program and prompt
user advice to the console
e189940c7e53f434f93c886fa19ec43a99a22c28

first output API function
red: 671df74eb195e9e558f9e51b95095eaf000009d0
green: 070a0f31f7870273842543668ca69f9a11ec1f21

Output_sendSMS() is triggered twice when used within MainUI.java
red: 28d2f3b4340217311ab64c903239b61aeebb817e
green: bf7e1abcbac0dc1f56013c3b8d69abf5bc96ea0a
// fixed the problem by setting the button to disable after one action has been handled


