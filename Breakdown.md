# Breakdown of project

## Description of the application
    - The application displays information related to raspberry pi's on tiles. All these devices communicate with the same server and the data published will be visible on everyones tiles. To utilise this application, one must type in their username and password for the mqtt client and only then will the GUI launch.

## List of features/functionalities of the application
    1. When a new temperature reading is read, the application will publish a new message with the information related to temperature and humidity and display it on the tile. This will be the same for all users connected to the client and their information will be displayed on their respective tile.
    2. When you press the doorbell, it will ring and a message will be published and displayed on its respective tile for all connected users, indicating that the doorbell was pressed. This message will also inidcate the timestamp of when it was pressed.
    3. Additonally upon clicking the doorbell, a picture will be taken and a message will be published on its respective tile for each user, indicating that a picture was taken at that specfic timestamp.
    4. Furthermore, the picture taken will be published and displayed on its tile for each user connected.
    5. To exit the application, the "x" button in the top right should be pressed.

