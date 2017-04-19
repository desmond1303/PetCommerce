# --- !Ups

CREATE TABLE IF NOT EXISTS "user" (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name character varying(64) NOT NULL,
	email character varying(254) NOT NULL UNIQUE,
	hash text,
	salt text,
	is_admin boolean DEFAULT false
);

CREATE TABLE IF NOT EXISTS item (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name character varying(64) NOT NULL,
	description text,
	price money,
	quantity integer
);

CREATE TABLE IF NOT EXISTS item_review (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	item_id UUID NOT NULL REFERENCES item(id) ON UPDATE CASCADE ON DELETE CASCADE,
	user_id UUID NOT NULL REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE,
	stars integer NOT NULL
);

CREATE TABLE IF NOT EXISTS photo (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	item_id UUID NOT NULL REFERENCES item(id) ON UPDATE CASCADE ON DELETE CASCADE,
	path text NOT NULL,
	main BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS "order" (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_id UUID NOT NULL REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE,
	date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS order_item (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	order_id UUID NOT NULL REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE,
	item_id UUID NOT NULL REFERENCES item(id) ON UPDATE CASCADE ON DELETE CASCADE,
	quantity integer
);

CREATE TABLE IF NOT EXISTS cart_item (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	user_id UUID NOT NULL REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE,
	item_id UUID NOT NULL REFERENCES item(id) ON UPDATE CASCADE ON DELETE CASCADE,
	quantity integer
);

# --- !Downs

DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS photo;
DROP TABLE IF EXISTS item_review;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS "user";