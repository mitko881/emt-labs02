INSERT INTO countries (name, continent) VALUES
                                            ('Macedonia', 'Europe'),
                                            ('Serbia', 'Europe'),
                                            ('Germany', 'Europe');

INSERT INTO hosts (created_at, updated_at, name, surname, country_id) VALUES
                                                                          (NOW(), NOW(), 'Ivan', 'Ivanov', 1),
                                                                          (NOW(), NOW(), 'Ana', 'Petrova', 2),
                                                                          (NOW(), NOW(), 'Marko', 'Markov', 3);

INSERT INTO accommodations (created_at, updated_at, name, category, condition, host_id, num_rooms, rented) VALUES
                                                                                                               (NOW(), NOW(), 'Villa Lux', 'HOUSE', 'GOOD', 1, 5, FALSE),
                                                                                                               (NOW(), NOW(), 'City Apartment', 'APARTMENT', 'GOOD', 2, 3, FALSE),
                                                                                                               (NOW(), NOW(), 'Old Motel', 'MOTEL', 'BAD', 3, 8, FALSE);