DROP TABLE post_attachments;

CREATE TABLE attachment
(
    attachment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    location      TEXT NOT NULL,
    original_filename TEXT NOT NULL
);

CREATE TABLE post_attachment (
    attachment_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,

    FOREIGN KEY (post_id) REFERENCES post (`post_id`),
    FOREIGN KEY (attachment_id) REFERENCES attachment (`attachment_id`)
);
