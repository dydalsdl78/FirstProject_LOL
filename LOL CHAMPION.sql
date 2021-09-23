drop table champion purge;
drop table position purge;
drop table champion_image;

create table position(
    position_id number(30) constraint champion_position_pk primary key,
    position_name varchar2(30),
    position_attack varchar2(30),
    position_defense varchar2(30)
    );

CREATE TABLE champion ( 
    champion_id number(30) constraint champion_pk primary key, 
    champion_name varchar2(30), 
    champion_attack_point number(30), 
    champion_defense_point number(30),
    champion_position_id number(30) constraint champion_fk references position(position_id)  
    ); --포지션 아이디 변수명 이름 바꿈

CREATE TABLE champion_image (
    champion_image_id number(30) primary key,
    champion_image_name varchar(30),
    champion_image_url varchar(200),
    champion_url varchar(100)
    );

CREATE INDEX champion_id_idx on champion(champion_name); 

create or replace synonym cham
    for champion;

drop sequence champion_id_seq;
create sequence champion_id_seq;    

drop sequence position_id_seq;
create sequence position_id_seq;   
   
drop sequence champion_image_id_seq;
create sequence champion_image_id_seq;
   
create or replace package champion_pack
is
    procedure champion_insert
   (p_champion_name              champion.champion_name%TYPE,
    p_champion_attack_point      champion.champion_attack_point%TYPE,
    p_champion_defense_point     champion.champion_defense_point%TYPE,
    p_champion_position_id       champion.champion_position_id%TYPE);
    
    procedure champion_update
    (p_champion_id                  champion.champion_id%TYPE,
     p_champion_attack_point        champion.champion_attack_point%TYPE,
     p_champion_defense_point       champion.champion_defense_point%TYPE);

    procedure champion_delete
    (p_champion_id   champion.champion_id%TYPE);

    procedure champion_select_all
    (p_cursor out sys_refcursor);
   
    procedure champion_random
    (p_cursor out sys_refcursor);
   
    procedure champion_select_image
    (p_champion_name in champion_image.champion_image_name%TYPE,
     p_champion_image_url out champion_image.champion_image_url%TYPE);
   
    procedure champion_search
    (p_in_champion_id in champion.champion_id%type,
     p_cursor out sys_refcursor);
    
    procedure champion_detail
    (p_in_champion_id in champion.champion_id%type,
     p_cursor out sys_refcursor);

  end;
  /
  
   create or replace package body champion_pack
  is

    procedure champion_insert
     (p_champion_name              champion.champion_name%TYPE,
      p_champion_attack_point      champion.champion_attack_point%TYPE,
      p_champion_defense_point     champion.champion_defense_point%TYPE,
      p_champion_position_id       champion.champion_position_id%TYPE)
    is
    begin
        insert into champion(champion_id, champion_name, champion_attack_point, champion_defense_point, champion_position_id)
        values(champion_id_seq.nextval, p_champion_name, p_champion_attack_point, p_champion_defense_point, p_champion_position_id);
        
      commit;
    end;

    procedure champion_update
    (p_champion_id                champion.champion_id%TYPE,
     p_champion_attack_point      champion.champion_attack_point%TYPE,
     p_champion_defense_point     champion.champion_defense_point%TYPE)
    is
    begin
      update champion
      set champion_attack_point =  p_champion_attack_point, 
          champion_defense_point = p_champion_defense_point
          
      where champion_id = p_champion_id;

      commit;
    end;    

    procedure champion_delete
    (p_champion_id champion.champion_id%TYPE)
    is
    begin
      delete from champion
      where champion_id = p_champion_id;

      commit;
    end;

    procedure champion_select_all
    (p_cursor out sys_refcursor)
    is
    begin
      open p_cursor for 
        select c.champion_id, c.champion_name, c.champion_attack_point, c.champion_defense_point, p.position_name, p.position_attack, p.position_defense from champion c, position p where p.position_id = c.champion_position_id;
    end;
    
    procedure champion_random
    (p_cursor out sys_refcursor)
    is
    begin
        open p_cursor for
        select * from (select * from champion order by dbms_random.random()) where rownum <= 2;
    end;
    
    procedure champion_select_image
    (p_champion_name in champion_image.champion_image_name%TYPE,
     p_champion_image_url out champion_image.champion_image_url%TYPE)
    is
    begin
        select champion_image_url into p_champion_image_url from champion_image where champion_image_name = p_champion_name;
        exception
        when NO_DATA_FOUND then
        select champion_image_url into p_champion_image_url from champion_image where champion_image_name = 'blank';
    end;
 
    procedure champion_search
    (p_in_champion_id in champion.champion_id%type,
     p_cursor out sys_refcursor)
    is
    begin
      open p_cursor for 
        select c.champion_id, c.champion_name, c.champion_attack_point, c.champion_defense_point, p.position_name, p.position_attack, p.position_defense from champion c, position p where p.position_id = c.champion_position_id and c.champion_id = p_in_champion_id;
    end;

    procedure champion_detail
    (p_in_champion_id in champion.champion_id%type,
     p_cursor out sys_refcursor)
    is
    begin
     open p_cursor for 
        select i.champion_image_url, c.champion_name, c.champion_attack_point, c.champion_defense_point, p.position_name, p.position_attack, p.position_defense, i.champion_url from champion c, position p, champion_image i where p.position_id = c.champion_position_id and c.champion_name = i.champion_image_name and c.champion_id = p_in_champion_id;
    end;
     
  end;
  /

  create or replace view vm_champion
    as
    (select champion_id, champion_name, champion_attack_point, champion_defense_point, champion_position_id
    from champion
    );


-- 포지션 데이터를 insert하는 프로시저 
-- create or replace procedure p_insert_position
--  is
--  begin
--      insert into position
--      values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'SWORD', 'ARMOR' );
--      insert into position
--      values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'MAGIC', 'AVOID');
--      insert into position
--      values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'RIFLE', 'AVOID');
--      insert into position
--      values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'SWORD', 'SHIELD');
--  end;
--  /
  
   -- 트리거
  create or replace trigger champion_tri
     after insert on champion
     for each row
     begin
        dbms_output.put_line('챔피언 생성');
  end;
  /
 
  insert into position
  values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'SWORD', 'ARMOR' );
  
  insert into position
  values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'MAGIC', 'AVOID');
  
  insert into position
  values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'RIFLE', 'AVOID');
  
  insert into position
  values(position_id_seq.nextval, position_attribute(position_id_seq.nextval), 'SWORD', 'SHIELD'); 
 
 
  --set verify off
  set serveroutput on

  truncate table champion;

  select * from champion;

  exec champion_pack.champion_insert('가렌', 100, 100, 1)
  exec champion_pack.champion_insert('말자하',120, 80, 2)
  exec champion_pack.champion_insert('베인',90, 50, 3)
  exec champion_pack.champion_insert('리 신', 120, 80, 4)

 -- 포지션 번호에 따라 포지션 이름을 return하는 function  
  create or replace function position_attribute(p_go in number)
    return varchar2
  is
    p_num number := p_go;
  begin
    if p_num = 1 then
        return 'TOP';
    
    elsif p_num = 2 then
        return 'MID';
  
    elsif p_num = 3 then
        return 'BOTTOM';

    else
        return 'JUNGLE';
   
  end if;  
  end;
  /

/*
  declare
    v_cursor     sys_refcursor;
    champion_rec champion%rowtype;
  begin
    champion_pack.champion_select_all(v_cursor);    

    loop
      fetch v_cursor into champion_rec;
      exit when v_cursor%notfound;
      dbms_output.put_line('-------------');
      dbms_output.put_line(champion_rec.champion_id);
      dbms_output.put_line(champion_rec.champion_name);
      dbms_output.put_line(champion_rec.champion_attack_point);
      dbms_output.put_line(champion_rec.champion_defense_point);
      dbms_output.put_line(champion_rec.champion_position_id);
    end loop;
 
    close v_cursor;
  end;
  /
*/  
  select * from vm_champion;
  
  
  declare
    champion_id number;
  begin  
    champion_id := champion_id_seq.nextval;
    
    dbms_output.put_line ('시퀀스 값: ' || to_char(champion_id));
  end;
  /
  
  
  select * from position;
  select * from champion;
  select * from champion_image;
  

 
 
commit;