This app is writed in Java 17 and SpringBoot 3.
In this app maded a email service for sending emails using kafka and elasticsearch.
To start app write in a console docker-compose up --build.
This app is connected to my last project in nodejs.
Link: https://github.com/v0sska/nodejs-songs-api
When you add a music in database you you recieved a email
example: 

          {
             "name": "your_song",
             "groupId": 1,
             "dateOfRelease": "02-02-2002"
          }

recieved message in your email:

      {
      _id: 6670adfe10b38b0e49a7f995,
      name: 'Bla',
      groupId: 1,
      dateOfRelease: 2002-02-01T22:00:00.000Z,
      __v: 0
    } has been added to the database

In docker-compose file change the envirement to smpt server

      - SPRING_MAIL_HOST=mail.smtp2go.com
      - SPRING_MAIL_PORT=2525
      - SPRING_MAIL_USERNAME=your_username
      - SPRING_MAIL_PASSWORD=your_passeord
      - SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
      - SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
      - SPRING_MAIL_PROPERTIES_MAIL_SMTP_FROM=your_email
      
And in application properties for sure

    spring.mail.host=mail.smtp2go.com
    spring.mail.port=2525
    spring.mail.username=your_username
    spring.mail.password=your_password
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.properties.mail.smtp.from=your_email
    spring.mail.test-connection=true

This app is load a 3 messages to kafka in start so for testing change email 

          @PostConstruct
      public void initMessages(){

        firstMessages.add(new MessagesToSend("1", "Content 1", "Subject 1", "emailtotest@example.com"));
        firstMessages.add(new MessagesToSend("2", "Content 2", "Subject 2", "emailtotest2@example.com"));
        firstMessages.add(new MessagesToSend("3", "Content 3", "Subject 3", "emailtotest3@example.com"));

    }
