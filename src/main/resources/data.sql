/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(),  false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- Create 6  manufacturers
insert into manufacturer (id, manufacturer_name) values (1, 'Daimler');
insert into manufacturer (id, manufacturer_name) values (2, 'Toyota');
insert into manufacturer (id, manufacturer_name) values (3, 'Volkswagen');
insert into manufacturer (id, manufacturer_name) values (4, 'Hyundai');
insert into manufacturer (id, manufacturer_name) values (5, 'Ford');
insert into manufacturer (id, manufacturer_name) values (6, 'Nissan');

--create 6 cars
insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID,DRIVER_ID)
   values (1, '35 ZZG 061', 2, 'STRAIGHT', false, 5.0, now(), false, 1,4);

insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID,DRIVER_ID)
    values (2, '35 ABC 000', 2, 'STRAIGHT', false, 5.0, now(), false, 1,5);

insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID,DRIVER_ID)
    values (3, '35 DEF 000', 2, 'STRAIGHT', false, 5.0, now(), false, 1,6);

insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID,DRIVER_ID)
    values (4, '61 FGH 000', 2, 'STRAIGHT', false, 5.0, now(), false, 1,8);

insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID)
    values (5, '61 ABC 000', 2, 'STRAIGHT', false, 5.0, now(), false, 1);

insert into car(id,license_plate,seat_count,engine_type,convertible,rating,date_updated,deleted,Man_ID)
    values (6, '61 FGH 123', 2, 'STRAIGHT', false, 5.0, now(), false, 1);
