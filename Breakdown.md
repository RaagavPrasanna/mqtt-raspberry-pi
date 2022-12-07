# Breakdown of project

## Description of the application
    - The application displays information related to raspberry pi's on tiles. All these devices communicate with the same server and the data published will be visible on everyones tiles. To utilise this application, one must type in their username and password for the mqtt client and only then will the GUI launch.

## List of features/functionalities of the application
    1. When a new temperature reading is read, the application will publish a new message with the information related to temperature and humidity and display it on the tile. This will be the same for all users connected to the client and their information will be displayed on their respective tile.
    2. When you press the doorbell, it will ring and a message will be published and displayed on its respective tile for all connected users, indicating that the doorbell was pressed. This message will also inidcate the timestamp of when it was pressed.
    3. Additonally upon clicking the doorbell, a picture will be taken and a message will be published on its respective tile for each user, indicating that a picture was taken at that specfic timestamp.
    4. Furthermore, the picture taken will be published and displayed on its tile for each user connected.
    5. To exit the application, the "x" button in the top right should be pressed.

## Required Functionalities that were not implemented
    - Background: 
        - Unfortunately 2 of us had fallen terribly sick recently for a good amount of time, and that really held us back on our work. As a result, we had to omit a few features for the sake of time and actually getting a functional application.
    - Passing the signature via the publish payload: 
        - Due to the lack of time, we had to omit the passing of signatures through publish messages. 
        - Currently, we have one keystore that signs and verifies all strings that are published as messages.
    - Proximity Sensor:
        - The proxmity sensor was a big pain. 
        - It would never register motion at the right time and when it did, it would be at a completley random moment. 
        - We felt as if it wasn't consistent enough and we wanted to properly be able to take a picture. 
        - Hence we ommitted this feature and implemented the picture taking along with the pressing of the buzzer. 
        - Perhaps if we really fiddled with it more we could of got it to work properly but for the sake of time, we omitted it.
    
    

