CREATE TABLE Users(
                      userid SERIAL,
                      username VARCHAR(100) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      created DATE,
                      enable BOOL,
                      PRIMARY KEY(userid)
);

CREATE TABLE token(
                      id SERIAL,
                      token VARCHAR(200),
                      expiryDate DATE,
                      PRIMARY KEY(id)
);



CREATE TABLE Subreddit (
                           id SERIAL,
                           name VARCHAR(200) NOT NULL,
                           description VARCHAR(2000) NOT NULL,
                           createdDate DATE,
                           PRIMARY KEY(id)
)

CREATE TABLE Post(
                     id SERIAL,
                     postName VARCHAR(100) NOT NULL,
                     url VARCHAR(200),
                     description VARCHAR(2000),
                     voteCount INT,
                     userid INT,
                     createdDate DATE,
                     Subreddit INT,
                     FOREIGN KEY(userid) REFERENCES Users(userid),
                     FOREIGN KEY(subreddit) REFERENCES Subreddit(id),
                     PRIMARY KEY(id)
);

CREATE TABLE Vote(
                     voteid serial,
                     postid INT NOT NULL,
                     userid INT,
                     FOREIGN KEY(postid) REFERENCES Post(id),
                     FOREIGN KEY(userid) REFERENCES Users(userid),
                     PRIMARY KEY(voteid)
);

CREATE TABLE Comment(
                        id serial,
                        text VARCHAR(200) NOT NULL,
                        postid INT ,
                        createdDate DATE,
                        userid INT,
                        FOREIGN KEY(postid) REFERENCES Post(id),
                        FOREIGN KEY(userid) REFERENCES Users(userid),
                        PRIMARY KEY(id)
)