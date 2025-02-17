CREATE TABLE IF NOT EXISTS customers (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS addresses (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    street_address VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    country VARCHAR(100) NOT NULL,
    postal_code VARCHAR(20),
    is_default BOOLEAN DEFAULT false,
    address_type VARCHAR(20) NOT NULL,
    customer_id UUID NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE INDEX IF NOT EXISTS idx_addresses_customer_id ON addresses(customer_id); 