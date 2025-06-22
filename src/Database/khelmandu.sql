CREATE DATABASE Khelmandu;
USE Khelmandu;

CREATE TABLE users(
id int auto_increment primary key,
f_name varchar(20),
l_name varchar(20),
ph_number long,
role VARCHAR(20),
password varchar(20)
);

CREATE TABLE Venue (
    id INT,
    venueName VARCHAR(255),
    venueContact VARCHAR(10),
    venueDescription TEXT,
    venueLocation VARCHAR(255),
    venuePrice INT,
    venueType VARCHAR(100),
    venueImage VARCHAR(500),
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE Bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    venue_id INT,
    booking_date DATE,
    time_slot VARCHAR(50),
    payment_type VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (venue_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE rules(
	id INT AUTO_INCREMENT PRIMARY KEY,
    game VARCHAR(20),
    rule TEXT
);










































-- Game Rules -----

INSERT INTO rules (game, rule) VALUES
-- Basketball
('basketball', 'Each team has five players on the court.'),
('basketball', 'The game starts with a jump ball.'),
('basketball', 'You score by shooting the ball through the opponent\'s hoop.'),
('basketball', 'A field goal is worth two or three points, depending on distance.'),
('basketball', 'The ball must be dribbled while moving.'),
('basketball', 'Traveling results in a turnover.'),
('basketball', 'Fouls result in free throws or possession.'),
('basketball', 'Each game is played in four quarters.'),
('basketball', 'The team with the most points wins.'),
('basketball', 'Substitutions are allowed during stoppages.'),

-- Futsal
('futsal', 'Futsal is played indoors on a hard court.'),
('futsal', 'Each team has five players, including the goalkeeper.'),
('futsal', 'There are no throw-ins—kick-ins are used instead.'),
('futsal', 'The ball must stay low and be controlled.'),
('futsal', 'Each half is 20 minutes long with a running clock.'),
('futsal', 'Sliding tackles are not allowed.'),
('futsal', 'A team is only allowed five fouls per half.'),
('futsal', 'After the 5th foul, the opponent gets a free kick without a wall.'),
('futsal', 'The goalkeeper can only hold the ball for 4 seconds.'),
('futsal', 'Goals cannot be scored directly from kick-ins.'),

-- Criksal
('criksal', 'Criksal is played on a futsal-like court with a cricket ball.'),
('criksal', 'Each team has six players.'),
('criksal', 'Players bowl underarm, and batsmen use a soft bat.'),
('criksal', 'Each innings has 6 overs.'),
('criksal', 'Runs are scored by running between wickets or hitting boundaries.'),
('criksal', 'The game uses simplified rules for bowling and scoring.'),
('criksal', 'No LBW rule applies in Criksal.'),
('criksal', 'Each bowler can bowl a maximum of 2 overs.'),
('criksal', 'Catches and clean bowled result in outs.'),
('criksal', 'The team with the most runs wins.'),

-- Table Tennis
('tabletennis', 'Each match is played up to 11 points per game.'),
('tabletennis', 'A match is best of 5 or 7 games.'),
('tabletennis', 'Players serve two times alternately.'),
('tabletennis', 'The ball must bounce once on each side during a serve.'),
('tabletennis', 'You must hold the paddle and ball above the table.'),
('tabletennis', 'A rally starts with a legal serve and continues until a fault.'),
('tabletennis', 'The ball must stay above and behind the table during serve.'),
('tabletennis', 'Edges of the table count, but sides do not.'),
('tabletennis', 'Games must be won by at least two points.'),
('tabletennis', 'You switch sides after each game.'),

-- Badminton
('badminton', 'Each game is played to 21 points.'),
('badminton', 'Players must win by at least two points.'),
('badminton', 'Matches are best of three games.'),
('badminton', 'Serve diagonally into the opposite service box.'),
('badminton', 'You only get one serve attempt per rally.'),
('badminton', 'The shuttle must not hit the floor on your side.'),
('badminton', 'Only one hit per side is allowed.'),
('badminton', 'You cannot touch the net with your body or racket.'),
('badminton', 'Change sides after each game.'),
('badminton', 'The shuttle is in if it lands on the line.');
