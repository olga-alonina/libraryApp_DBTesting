select count(*)
from users;

select count(*)
from books;

select count(*)
from book_borrow
where is_returned = 0;

select name
from book_categories;

select *
from books
where name = 'Clean Code';

select name, author, isbn, description, year
from books
where name = 'Clean Code';
/*to get all IDs from users=611*/
select count(id)
from users;
/*US1 verify users has unique IDs=611*/
select count(distinct id)
from users;
/*Execute query to get all columns*/
select *
from users;
/*us2 amount non returned borrowed book*/
select count(*)
from book_borrow bb
where returned_date is null;
/*us3 book_categories table from db*/
select name
from book_categories;
/*us04 book information*/
select b.name, author, isbn, b.description, b.year, bc.id
from books b
         join book_categories bc on b.book_category_id = bc.id
where b.name = 'Clean Code'
  and isbn = 999;
/*us05 most borrowed book*/
select bc.name, count(*)
from book_borrow bb
         inner join books b on bb.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

/*us 06 add book*/
select id, name, author
from books
where name = 'Clean Code'
order by id desc;
select id, name, author
from books
where name = 'Head First Java'
order by id desc;
select id, name, author
from books
where name = 'The Scrum Field Guide'
order by id desc;
/*us07 student borrow book*/
select full_name, b.name, bb.borrowed_date
from users u
         inner join book_borrow bb on u.id = bb.user_id
         inner join books b on bb.book_id = b.id
where name = 'zxc'
order by 3 desc;
