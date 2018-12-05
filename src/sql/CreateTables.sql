USE swarmexplore;
DROP TABLE IF EXISTS bots;
DROP TABLE IF EXISTS points;
DROP TABLE IF EXISTS map;

CREATE TABLE bots ( 
	id INT PRIMARY KEY,
    posx INT NOT NULL,
    posy INT NOT NULL,
    velx INT NOT NULL,
    vely INT NOT NULL,
    rotx DOUBLE NOT NULL,
    roty DOUBLE NOT NULL,
    step INT DEFAULT 0
);
CREATE TABLE map (
	id INT PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL
);
CREATE TABLE points (
	mapId INT NOT NULL,
    x INT NOT NULL,
    y INT NOT NULL,
    barrier BOOL NOT NULL,
    PRIMARY KEY (x, y),
    FOREIGN KEY (mapId) REFERENCES map(id)
);
