CREATE DATABASE IF NOT EXISTS school ;
use school ;

DELIMITER $$

DROP FUNCTION IF EXISTS GET_RANDOM_GENDER;
CREATE FUNCTION GET_RANDOM_GENDER() RETURNS VARCHAR(20)
NOT DETERMINISTIC
BEGIN

    SET @genders = 'Male,Female';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 2 )) );
    SET @pre_result = SUBSTRING_INDEX(@genders, ',', @item_index);
    SET @result = SUBSTRING_INDEX(@pre_result, ',', -1);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_FIRSTNAME;
CREATE FUNCTION GET_RANDOM_FIRSTNAME() RETURNS VARCHAR(20)
NOT DETERMINISTIC
BEGIN

    SET @firstnames =
'Kissiah,Chet,Sheeree,Gavin,Koral,Thomasin,Brien,Leticia,Vanni,Winona,Shirl,Lexie,Livvy,Hale,Gael,Gill,Maire,Ilene,Hatty,
Jeniffer,Prudi,Vinny,Tabb,Birch,Sheilah,Goddart,Dorelia,Caty,Ann-marie,Eudora,Leyla,Prisca,Gerald,Tedra,Amie,Cassondra,
Hurley,Maynord,Sumner,Arri,Jack,Carlin,Gisela,Bessie,Emlyn,Dalli,Arabella,Nahum,Vanna,Eddie,Leslie,Georgine,Elysia,Tomasina,
Valerye,Evanne,Gratiana,Vidovic,Llewellyn,Romona,Sheila-kathryn,Janessa,Bianca,Robyn,Dasya,Patten,Nalani,Adan,Malena,
Griswold,Averell,Gare,Jessie,Brier,Emmeline,Emmalee,Babara,Shepard,Hendrika,Devan,Cris,Elwyn,Brockie,Quinlan,Haroun,
Janean,Doreen,Sherline,Dorian,Ramsey,Leah,Carline,Torre,Milzie,Lonny,Maible,Stoddard,Polly,Chevy,Nell,Demetris,Niven,
Boyd,May,Olly,Riobard,Laetitia,Joycelin,Gordy,Domingo,Melisse,Duffie,Sherlocke,Sheelah,Cob,Millard,Wendeline,Ginnifer,
Robinette,Brigitta,Clayton,Antonella,Bert,Hyman,Janette,Germaine,Codie,Cherin,Eryn,Jilli,Goldarina,Scarlet,Scot,Karia,
Paulina,Doyle,Petra,Nilson,Lindsey,Lauren,Morrie,June,Gannon,Ellen,Rex,Mart,Ebonee,Arabela,Morgen,Kate,Ty,Annemarie,Priscella,
Isador,Jaquelyn,Lonny,Shalne,Allina,Tiffie,Tedie,Eddy,Gracia,Morse,Starr,Cheston,Chris,Tedman,Kennie,Minny,Lee,Merrily,
Olympie,Travis,Cornie,Alex,Zahara,Westleigh,Mikkel,Danica,Suzann,Laverne,Tabbatha,Enrica,Easter,Jacques,Ajay,Frayda,
Bernice,Eli,Greggory,Nowell,Rosemary,Carmelle,Valeria,Claudius,Norris,Carmelina,Will,Emlynne,Lorettalorna,Emelina,
Peirce,Cori,Beverly,Olvan,Auria,Carlotta,Alyss,Maudie,Shaw,Jonathon,Riccardo,Fabio,Keelby,Gwenette,Mirabella,Jimmie,
Silvanus,Saxe,Zorah,Shellie,Mordy,Norene,Kerri,Alain,Ignacius,Tonie,Cordy,Breanne,Bernadina,Margalo,Stearne,Belle,
Zorana,Gawen,Donna,Alicia,Hartley,Sumner,Eba,Nerita,Brent,Coralie,Dominga,Arliene,Zachariah,Rad,Rosita,Corly,Gisela,
Merrie,Giffer,Baron,Brandtr,Monro,Garek,Carroll,Chelsea,Sam,Rebekah,Bertrand,Frederigo,Nolana,Leda,Cyrill,Danila,Jerald,
Inna,Mohandis,Valentine,Kelley,Addy,Jennilee,Durand,Colleen,Bibby,Arthur,Fletch,Frieda,Sisile,Joelie,Onfroi,Rozella,Kerrill,
Christyna,Rusty,Krystle,Benita,Emerson,Harold,Oliy,Pat,Heddie,Claus,Noble,Vivi,Tootsie,Traci,Cherin,Giffy,Gracia,Irwin,
Renault,Kara-lynn,Yasmeen,Murry,Elbertine,Mac,Turner,Mauricio,Norene,Chrisse,Andres,Billy,Dyana,Sam,Suzann,Georgianna,Opaline,
Caroline,Jehanna,Peder,Steffen,Breanne,Lezlie,Melodee,Arabele,Jayme,Hollis,Murvyn,Rayshell,Pete,Fleur,Gannie,Edith,Wilow,
Pru,Barthel,Vance,Forest,Roscoe,Gerome,Enrichetta,Alicea,Padgett,Concordia,Xever,Kaitlin,Ellynn,Margette,Catherina,Cointon,
Martina,Perceval,Charlean,Haydon,Rivi,Marietta,Cornie,Woodman,Allison,Rhea,Phoebe,Amy,Selia,Olympie,Ruthie,Dorie,Ermentrude,
Taryn,Kurt,Brannon,Brandea,Korella,Dorita,Lucien,Elane,Christophe,Ame,Edna,Gae,Jewell,Dino,Heinrick,Lesya,Chilton,Darbee,
Beilul,Eloise,Stacy,Quinta,Carlina,Franklyn,Karen,Gerard,Griz,Timmie,Caleb,Lenora,Tamar,Ula,Otto,Coletta,Clarabelle,Sarena,
Antony,Lynnell,Parry,Dodi,Shelba,Gwenni,Sidoney,Jerome,Sid,Carmelita,Lucinda,Ulises,Jeanelle,Idette,Sallyann,Layton,Dannie,
Shaughn,Rubin,Catlaina,Dukie,Janet,Querida,Jake,Gasparo,Brooke,Reece,Lewiss,Drew,Manny,Jared,Perkin,Nelson,Hannie,Jacques,
Aundrea,Bibbye,Claudius,Shadow,Ninnetta,Barny,Dorolice,Kean,Kristin,Nicola,Katerina,Cozmo,Florry,Addy,Omar,Dollie,Odella,
Patsy,Meris,Cheri,Bevin,Ailis,Renato,Harold,Martina,Lilyan,Antoinette,Vlad,Amble,Yardley,Ursuline,Anne-corinne,Rosette,
Anthe,Harmon,Xymenes,Chris,Valle,Tiebold,Carleton,Ab,Tiphanie,Bord,Gabby,Hewet,Raychel,Tam,Torrey,Pepe,Kareem,Aurlie,Olimpia,
Freeland,Meredeth,Del,Thomasine,Greer,Fifi,Russell,Elena,Nyssa,Ker,Jeniece,Emilie,Rory,Ringo,Paula,Pietra,Cosmo,Imojean,Morganne,
Cissy,Buck,Chevalier,Hermione,Donall,Reg,Heather,Valida,Catharina,Carlyle,Layla,Forster,Sauncho,Pieter,Jeremy,Errick,Rosa,
Fonz,Isaiah,Ferd,Domenic,Brigg,Didi,Jarret,Levin,Petrina,Mari,Mischa,Roxine,Ailey,Susanetta,Averil,Babita,Andra,Sophronia,
Tobi,Reynard,Umberto,Torrie,Ryan,Jayson,Krystalle,Caterina,Lorrayne,Napoleon,Ravi,John,Eydie,Clea,Lemmy,Martina,Anna-maria,Colline';

    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 500 )) );
    SET @pre_result = SUBSTRING_INDEX(@firstnames, ',', @item_index);
    SET @result = SUBSTRING_INDEX(@pre_result, ',', -1);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_LASTNAME;
CREATE FUNCTION GET_RANDOM_LASTNAME() RETURNS VARCHAR(20)
NOT DETERMINISTIC
BEGIN

    SET @lastnames =
'Willcox,Boulding,Bounde,Prop,Dulake,Pollendine,Randerson,Jedrzej,Tether,Litchfield,Remirez,Moxley,Sambles,Ivchenko,Pleass,Paur,Marlor,
Cluney,Albinson,Tooth,Hebborne,Punch,Smorthwaite,Wapol,Cush,Poznan,Umfrey,De Vaar,Lissandrini,Janovsky,Minshaw,Barrick,
Dincke,Sambedge,Orto,Louch,Leith-Harvey,Slafford,Rigg,Kenyon,Roylance,Enser,Anstead,Poles,Saint,Cadding,Trendle,Nials,Fowden,
Mungane,Andrys,Levay,Mossbee,Kleinzweig,Isherwood,Semour,Risom,Cumine,Figurski,Grane,Figger,Greystock,Edgeller,Muzzall,
Saffrin,Sisley,Cadwaladr,Varker,Jump,Ganley,Glenn,Marron,Wyne,Mayston,Battista,O''Carrol,Neles,Drewry,Milthorpe,Kellick,
Gatecliff,Torbett,Lambarth,Fears,Ambrosio,Gurney,Aspole,Myrick,Cuel,Yitshak,Pering,Bindin,Choppen,Beasleigh,Ferenc,Defraine,
Rutherford,Meechan,Hamshaw,Ferreiro,Waldie,Losel,Rohmer,Marney,Floyed,Caswell,Wetherhead,Sarfati,Jumonet,Tumilson,Vigours,
Camillo,Haggard,Tezure,McVane,Bickerstasse,Marjot,Gadsdon,Clogg,Coffelt,Bartolomucci,Tipler,Regorz,Hainsworth,Beastall,
Battrum,Shone,McCome,Spenceley,Szymoni,Kyncl,Every,Cuesta,Canas,Monkeman,Ranyard,Etock,Tedridge,Larham,Knuckles,Duplain,
Cordey,Cowterd,Schrinel,Elcom,Jirak,Lared,Windows,Durrand,Bartolomeo,Mascall,Etty,Commucci,Tackle,Glanders,Vida,Slate,
Oldknowe,Fearn,Hustings,De Ruel,Halliwell,Smerdon,Berriball,Tullis,Murkitt,Ferrier,Veschambes,Scrivens,Aphale,Saterthwait,
O''Crowley,Spellward,Andrat,Fearnyough,Saxby,Sheal,Seiler,Dallywater,Palluschek,Charker,Lackham,Spearett,Lawee,Praundl,
Reina,Witchard,Rillstone,Gristwood,Usherwood,Parish,Brotherhead,Coughtrey,Jira,Cosyns,Coleford,Alforde,Beaulieu,Fegan,
Nowland,McAllester,Hewins,Edwardson,Bulter,Cullnean,Simony,Partridge,Epdell,Bunclark,Spenley,Lydden,Averay,Grishankov,
Aulton,Brotherton,Dank,Ditzel,Yuryichev,Seagrove,O'' Hogan,Matijasevic,Bleasby,MacAdie,Corballis,Millam,Whyteman,Vaughton,
Seiller,Booth-Jarvis,Darinton,Glasbey,Herche,Saddleton,Syrie,Halleybone,Dowdell,Brace,Brimblecombe,Ship,Woodley,Melling,
Powdrill,Devas,Haisell,Bard,Salvage,Reboul,Yter,Sproul,McIsaac,Monard,Blakebrough,Correa,Longhorn,Percifull,Rimell,Screas,
Schusterl,Shalliker,Bleddon,Piquard,McQuie,Dare,Anton,Secrett,Crosham,Shuttlewood,Rudsdale,Koppe,Fearnill,Roadknight,
Berndtssen,Tireman,Leile,Have,Bakhrushin,Challes,Diloway,Roscow,Matton,La Batie,Kilfeather,Eyeington,Murdy,MacKaig,Welds,
Tomaskunas,Gorvin,Afield,Capner,De Micoli,Lawee,Cuerdale,Hothersall,Conklin,Dealey,Yokelman,Forsdike,Chin,Deeks,Woodrooffe,
Sangwin,Stair,Scrivinor,Valentine,Rainton,Ciobotaru,Friedenbach,Carrol,Fer,de Mendoza,Georgeson,Corbyn,Wornham,Lorait,McGuckin,
Ricart,Cajkler,Emer,Peattie,Matthensen,Muckle,Ropking,Collimore,Lankester,McGilmartin,Walling,McCowen,Fieldhouse,Smallridge,
Mityakov,Domek,Grier,Downgate,Garling,Spender,Brocklesby,Girdwood,Husher,Avison,Drew,Arlett,Clissett,Monday,Wycliffe,
Schimpke,Swayton,Pawels,MacAleese,Brightie,Doulton,McGing,Stormouth,Gwyllt,Lukianov,Huffer,Groucutt,Birtwistle,Cortin,
Norton,Streak,Rodolf,Hartzenberg,Baynam,McAlester,Caneo,Shurmer,Spirritt,Gretton,Jandel,Whillock,Sommerville,Haye,Wheeler,
Warlton,Keave,Collete,Hosten,Ashbe,Chitson,Marrow,Boaler,Showte,Rosander,Salack,Rushby,Loney,McHardy,Leyshon,Kingswoode,
Galpin,Treven,Taile,Kelk,Sanz,Grahamslaw,Ivatt,Fairbard,Harraway,Lyle,Kearns,Killik,Tyce,Kem,Wyman,Aylmore,Presslee,
Hebbard,Kubek,Folonin,Lowseley,Dawks,Potebury,Cashley,Louw,Goskar,Buick,Eisikowitz,Lackington,Malyj,Simonsson,Peerman,
Washington,Grigaut,Snookes,McFaul,Bezzant,Signorelli,Lawles,Guynemer,Haszard,Snaith,Bellison,Chillingworth,Wallwork,Creasey,
D''Adda,Mc Kellen,Ryley,Basten,Wermerling,Finneran,Storr,Screeton,Jumont,Laughtisse,Bakhrushkin,Garrow,Skains,O''Bradden,
Breeds,Healks,Ekless,Gallafant,Moatt,Dorrell,Forstall,Sydney,Humbatch,Stanlake,Blaydon,Mosey,Pennells,MacKartan,Oolahan,
Iacovaccio,Reveley,Cockett,Greenless,Ellingham,Rouf,Philpots,Spata,Echallie,Vizor,Siggery,Yeatman,Bysouth,Westwick,Antyshev,
Anthon,Spanton,Jouannisson,O''Kinedy,Hylands,Dupre,Marcq,Greenacre,Armes,Drakeley,Sproson,Gatman,McCoole,Cattermoul,Sollowaye,
Haxby,Henner,Samet,Blodget,Bengall';

    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 500 )) );
    SET @pre_result = SUBSTRING_INDEX(@lastnames, ',', @item_index);
    SET @result = SUBSTRING_INDEX(@pre_result, ',', -1);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_BIRTHDATE;
CREATE FUNCTION GET_RANDOM_BIRTHDATE(min_age INTEGER, max_age INTEGER) RETURNS DATE
NOT DETERMINISTIC
BEGIN

    -- Now() - @year_base + @year_delta - @week_delta

    SET @min_age = min_age;
    SET @max_age = max_age;
    SET @week_delta = (SELECT FLOOR(1 + (RAND() * 52)) );
    SET @year_delta = (SELECT (FLOOR( 1 + RAND( ) * (@max_age - @min_age) )) );
    SET @year_base = @max_age;

    SET @pre_result = now() - INTERVAL @year_base YEAR + INTERVAL @year_delta YEAR + INTERVAL @week_delta WEEK;
    SET @result = (SELECT DATE_FORMAT(@pre_result, '%m/%d/%Y') );

    return STR_TO_DATE(@result, '%m/%d/%Y');
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_MARK_DATE;
CREATE FUNCTION GET_RANDOM_MARK_DATE(week_interval INTEGER) RETURNS DATE
NOT DETERMINISTIC
BEGIN

    -- Now() - @week_delta

    SET @week_interval = week_interval;
    SET @week_delta = (SELECT FLOOR(1 + (RAND() * @week_interval)) );

    SET @pre_result = now() - INTERVAL @week_delta WEEK;
    SET @result = (SELECT DATE_FORMAT(@pre_result, '%m/%d/%Y') );

    return STR_TO_DATE(@result, '%m/%d/%Y');
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_EMAIL;
CREATE FUNCTION GET_RANDOM_EMAIL() RETURNS VARCHAR(100)
NOT DETERMINISTIC
BEGIN

    SET @email_postfixes = '@gmail.com,@inbox.lv,@10minutesmail.com,@protonmail.com';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 3 )) );
    SET @pre_result = SUBSTRING_INDEX(@email_postfixes, ',', @item_index);
    SET @email_postfix = SUBSTRING_INDEX(@pre_result, ',', -1);

    SET @result = CONCAT(GET_RANDOM_FIRSTNAME(), '.', GET_RANDOM_LASTNAME(), @email_postfix);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_EMAIL;
CREATE FUNCTION GET_EMAIL(firstname VARCHAR(30), lastname VARCHAR(30)) RETURNS VARCHAR(100)
NOT DETERMINISTIC
BEGIN

    SET @firstname = firstname;
    SET @lastname = lastname;
    SET @email_postfixes = '@gmail.com,@inbox.lv,@10minutesmail.com,@protonmail.com';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 3 )) );
    SET @pre_result = SUBSTRING_INDEX(@email_postfixes, ',', @item_index);
    SET @email_postfix = SUBSTRING_INDEX(@pre_result, ',', -1);

    SET @result = CONCAT(@firstname, '.', @lastname, @email_postfix);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_CLASS_TYPE;
CREATE FUNCTION GET_RANDOM_CLASS_TYPE() RETURNS VARCHAR(25)
NOT DETERMINISTIC
BEGIN

    SET @class_types = 'Mathematical,Lingual,Basic,Correctional';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 4 )) );
    SET @pre_result = SUBSTRING_INDEX(@class_types, ',', @item_index);
    SET @result = SUBSTRING_INDEX(@pre_result, ',', -1);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_CLASS_NAME;
CREATE FUNCTION GET_RANDOM_CLASS_NAME() RETURNS VARCHAR(5)
NOT DETERMINISTIC
BEGIN

    SET @class_types = 'A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) * 22 )) );
    SET @pre_result = SUBSTRING_INDEX(@class_types, ',', @item_index);
    SET @letter_index = SUBSTRING_INDEX(@pre_result, ',', -1);

    SET @class_number = (SELECT (FLOOR( 1 + RAND( ) * 12 )) );
    SET @result = CONCAT(@class_number,@letter_index);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_SUBJECT;
CREATE FUNCTION GET_RANDOM_SUBJECT() RETURNS VARCHAR(25)
NOT DETERMINISTIC
BEGIN
    SET @subject_num = (select count(*) from subjects);
    SET @subjects = 'Math,Literature,Sport,English,Latvian,Russian,Biology,Physics,Chemistry,History,Geography,Economics';
    SET @item_index = (SELECT (FLOOR( 1 + RAND( ) *  @subject_num)) );
    SET @pre_result = SUBSTRING_INDEX(@subjects, ',', @item_index);
    SET @result = SUBSTRING_INDEX(@pre_result, ',', -1);

    RETURN @result;
END$$

DROP FUNCTION IF EXISTS GET_RANDOM_SUBJECT_ID;
CREATE FUNCTION GET_RANDOM_SUBJECT_ID() RETURNS INTEGER
NOT DETERMINISTIC
BEGIN
    SET @subject_num = (select count(*) from subjects);
    SET @result = (SELECT (FLOOR( 1 + RAND( ) *  @subject_num)) );

    RETURN @result;
END$$

DELIMITER ;