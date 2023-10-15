-- Insert para BRAND
INSERT INTO BRAND (BRAND_ID, NAME) VALUES (1, 'ZARA');
INSERT INTO BRAND (BRAND_ID, NAME) VALUES (2, 'ZARA HOME');

-- Inserts para PRODUCT
INSERT INTO PRODUCT (PRODUCT_ID, NAME) VALUES (35455, 'Producto 1');
INSERT INTO PRODUCT (PRODUCT_ID, NAME) VALUES (35456, 'Producto 2');


-- Inserts para PRICEDETAIL
INSERT INTO PRICE_DETAIL (PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURR) VALUES (1, TIMESTAMP '2020-06-14T00.00.00', TIMESTAMP '2020-12-31T23.59.59', 0, 35.50, 'EUR');
INSERT INTO PRICE_DETAIL (PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURR) VALUES (2, TIMESTAMP '2020-06-14T15.00.00', TIMESTAMP '2020-06-14T18:30:00', 1, 25.45, 'EUR');
INSERT INTO PRICE_DETAIL (PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURR) VALUES (3, TIMESTAMP '2020-06-15T00:00:00', TIMESTAMP '2020-06-15T11:00:00', 1, 30.50, 'EUR');
INSERT INTO PRICE_DETAIL (PRICE_LIST, START_DATE, END_DATE, PRIORITY, PRICE, CURR) VALUES (4, TIMESTAMP '2020-06-15T15:00:00', TIMESTAMP '2020-12-31T23:59:59', 1, 38.95, 'EUR');

-- Inserts para PRICE
INSERT INTO PRICE (PRICE_ID, BRAND_ID, PRODUCT_ID, PRICE_LIST)
VALUES (1, 1, 35455, 1);

INSERT INTO PRICE (PRICE_ID, BRAND_ID, PRODUCT_ID, PRICE_LIST)
VALUES (2, 1, 35455, 2);

INSERT INTO PRICE (PRICE_ID, BRAND_ID, PRODUCT_ID, PRICE_LIST)
VALUES (3, 1, 35455, 3);

INSERT INTO PRICE (PRICE_ID, BRAND_ID, PRODUCT_ID, PRICE_LIST)
VALUES (4, 1, 35455, 4);
