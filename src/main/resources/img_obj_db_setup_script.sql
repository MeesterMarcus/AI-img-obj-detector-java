-- Create table for ObjectEntity
CREATE TABLE IF NOT EXISTS objects (
                                       id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                       name VARCHAR(255) NOT NULL
    );

-- Create table for ImageMetadataEntity
CREATE TABLE IF NOT EXISTS image_metadata_entity (
                                                     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                                     image_source VARCHAR(255),
    label VARCHAR(255)
    );

-- Create a join table for the many-to-many relationship between ImageMetadataEntity and ObjectEntity
CREATE TABLE IF NOT EXISTS image_object_mapping (
                                                    image_id BIGINT,
                                                    object_id BIGINT,
                                                    PRIMARY KEY (image_id, object_id),
    CONSTRAINT fk_image
    FOREIGN KEY(image_id)
    REFERENCES image_metadata_entity(id)
    ON DELETE CASCADE,
    CONSTRAINT fk_object
    FOREIGN KEY(object_id)
    REFERENCES objects(id)
    ON DELETE CASCADE
    );
