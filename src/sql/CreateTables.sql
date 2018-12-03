USE swarmexplore;
CREATE TABLE bots ( 
	id INT PRIMARY KEY,
    posx INT NOT NULL,
    posy INT NOT NULL,
    velx INT NOT NULL,
    vely INT NOT NULL,
    rotx INT NOT NULL,
    roty INT NOT NULL,
    bump BOOL NOT NULL
);
CREATE TABLE map (
	id INT PRIMARY KEY AUTO_INCREMENT,
    width INT NOT NULL,
    height INT NOT NULL
);
CREATE TABLE points (
	mapId INT NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    explored BOOL NOT NULL,
    PRIMARY KEY (mapId),
    FOREIGN KEY (mapId) REFERENCES map(id)
);
