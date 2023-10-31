-- Create a new database
CREATE DATABASE ${DB_NAME};

-- Connect to the new database
\c ${DB_NAME};

-- Create a new user and set their password
CREATE USER ${DB_USERNAME} WITH PASSWORD ${DB_PASSWORD};

-- Grant necessary privileges to the user (e.g., SELECT, INSERT, UPDATE, DELETE)
GRANT ALL PRIVILEGES ON DATABASE ${DB_NAME} TO ${DB_USERNAME};
