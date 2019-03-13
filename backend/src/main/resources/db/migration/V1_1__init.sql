CREATE TABLE role
(
  role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user
(
  user_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
  email      VARCHAR(255) UNIQUE NOT NULL,
  password   VARCHAR(255)        NOT NULL,
  avatar_url TEXT,
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  role_id    BIGINT,

  FOREIGN KEY (role_id) REFERENCES role (`role_id`)
);

CREATE TABLE class_group
(
  class_group_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name           VARCHAR(255),
  created_at     DATETIME DEFAULT NOW(),
  created_by     BIGINT,

  FOREIGN KEY (created_by) REFERENCES user (`user_id`)
);

CREATE TABLE user_group
(
  class_group_id BIGINT NOT NULL,
  user_id        BIGINT NOT NULL,
  join_date      DATETIME DEFAULT NOW(),

  PRIMARY KEY (class_group_id, user_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (class_group_id) REFERENCES class_group (class_group_id)
);

CREATE TABLE notification
(
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  title      VARCHAR(255) NOT NULL,
  text       TEXT,
  link_href  TEXT,
  created_at DATETIME DEFAULT NOW(),
  visited    SMALLINT,
  user_id    BIGINT,

  FOREIGN KEY (user_id) REFERENCES user (`user_id`)
);


CREATE TABLE post
(
  post_id        BIGINT PRIMARY KEY AUTO_INCREMENT,
  class_group_id BIGINT   NOT NULL,
  created_at     DATETIME NOT NULL DEFAULT NOW(),
  created_by     BIGINT   NOT NULL,
  text           TEXT     NOT NULL,

  FOREIGN KEY (class_group_id) REFERENCES class_group (`class_group_id`),
  FOREIGN KEY (created_by) REFERENCES user (`user_id`)
);

CREATE TABLE post_attachments
(
  id       BIGINT PRIMARY KEY AUTO_INCREMENT,
  post_id  BIGINT NOT NULL,
  location TEXT   NOT NULL,

  FOREIGN KEY (post_id) REFERENCES post (`post_id`)
);

CREATE TABLE comment
(
  id         BIGINT PRIMARY KEY AUTO_INCREMENT,
  post_id    BIGINT   NOT NULL,
  created_by BIGINT   NOT NULL,
  created_at DATETIME NOT NULL DEFAULT NOW(),
  text       TEXT     NOT NULL,

  FOREIGN KEY (post_id) REFERENCES post (`post_id`),
  FOREIGN KEY (created_by) REFERENCES user (`user_id`)
);



