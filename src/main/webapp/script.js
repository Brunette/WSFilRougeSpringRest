/**
 * 
 */

function charger_films() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				afficher_film(request.responseText);
			}
		}
	};

	request.open("GET", "http://localhost:8080/FilRougeHib/rest/films", true);
	request.send();
}

function afficher_film(response) {
	var contenuJSON = JSON.parse(response);
	var ul_liste_film = document.getElementById('liste_film');
	var template = document.getElementById('filmbox_template');
	ul_liste_film.innerHTML = "";
	for (i = 0; i < contenuJSON.length; i++) {
		var clone = document.importNode(template.content, true);
		var div = clone.querySelector('div');
		var title = div.querySelector('input[name="title"]');
		var content = div.querySelector('textarea');
		var image = div.querySelector('img');
		var genres = div.querySelector('div[class="genrebox"]');
		var thumb = div.querySelector('input[name="thumblink"]');
		var newgenre = div.querySelector('input[name="newgenre"]');
		var duration = div.querySelector('input[name="duration"]');
		var genreBtn = div.querySelector('input[name="newgenrebtn"]');
		var suppBtn = div.querySelector('input[name="delete_film"]');
		var modBtn = div.querySelector('input[name="modif_film"]');
		var supfunc = "supprimer_film(" + contenuJSON[i].id + ")";
		var modfunc = "modifier_film(" + contenuJSON[i].id + ")";
		suppBtn.setAttribute("onclick", supfunc);
		modBtn.setAttribute("onclick", modfunc);
		genreBtn.setAttribute("onclick", "ajouter_genre(" + contenuJSON[i].id + ")");
		div.setAttribute("id", contenuJSON[i].id);
		title.value = contenuJSON[i].name;
		content.textContent += contenuJSON[i].info;
		duration.value = contenuJSON[i].duration;
		image.setAttribute("src", "movie/" + contenuJSON[i].thumbLink);
		thumb.value = contenuJSON[i].thumbLink;
		image.setAttribute("alt", contenuJSON[i].thumbLink);
		genres.setAttribute("id", 'genresbox_' + contenuJSON[i].id);
		newgenre.setAttribute("id", 'newgenre_' + contenuJSON[i].id);
		thumb.setAttribute("id", 'img_' + contenuJSON[i].id);
		for (g = 0; g < contenuJSON[i].genres.length; g++) {
			genres.innerHTML += '<div class="genres" id="genres_' + i + '_' + g + '">' +
				'<span class="genretag">' + contenuJSON[i].genres[g].name + '</span>' +
				'<input type="hidden" name="genrename" value="' + contenuJSON[i].genres[g].name + '"/>' +
				'<input class="genredelete" type="button" onclick="deleteGenreTag(\'genres_' + i + '_' + g + '\')" value="x"/>';
			genres.innerHTML += '</div>';
		}
		ul_liste_film.appendChild(div);
	}

}

function ajouter_genre(filmid) {

	var genres = document.getElementById('genresbox_' + filmid);
	var newgenre = document.getElementById('newgenre_' + filmid);
	var genresCount = genres.childElementCount;
	genres.innerHTML += '<div class="genres" id="genres_' + filmid + '_' + genresCount + '">' +
		'<span class="genretag">' + newgenre.value + '</span>' +
		'<input type="hidden" name="genrename" value="' + newgenre.value + '"/>' +
		'<input class="genredelete" type="button" onclick="deleteGenreTag(\'genres_' + filmid + '_' + genresCount + '\')" value="x"/>';
	genres.innerHTML += '</div>';
	newgenre.value = "";
}

function ajouter_film() {
var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200)
				afficher_message(request.responseText, "ajouté");
			charger_films();
		}
	}
	
	var film = document.getElementById("id_NewFilm");

	var title = film.querySelector('input[name="title"]').value;
	var genreBox = film.querySelector('div[class="genrebox"]');
	var genres = genreBox.querySelectorAll('input[name="genrename"]');
	var thumb = film.querySelector('input[name="thumblink"]').value;
	var duration = film.querySelector('input[name="duration"]').value;
	var genreStr = "";
	for (i = 0; i < genres.length; i++) {		
		genreStr += genres[i].value;
		if (i < genres.length - 1)
			genreStr += ',';
	}

	//var title = film.querySelector('h3').innerHTML;
	var info = film.querySelector('textarea').value;
	var filmdata = "&name=" + title + "&info=" + info + "&thumblink=" + thumb + "&duration=" + duration + "&genres=" + genreStr;
	alert(filmdata);
	request.open("POST", "http://localhost:8080/FilRougeHib/rest/films", true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(filmdata);
}

function modifier_film(filmid) {
	
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200)
				afficher_message(request.responseText, "modifié");
			charger_films();
		}
	}
	
	var film = document.getElementById(filmid);

var title = film.querySelector('input[name="title"]').value;
	var genreBox = film.querySelector('div[class="genrebox"]');
	var genres = genreBox.querySelectorAll('input[name="genrename"]');
	var thumb = film.querySelector('input[name="thumblink"]').value;
	var duration = film.querySelector('input[name="duration"]').value;
	var genreStr = "";
	for (i = 0; i < genres.length; i++) {		
		genreStr += genres[i].value;
		if (i < genres.length - 1)
			genreStr += ',';
	}

	//var title = film.querySelector('h3').innerHTML;
	var info = film.querySelector('textarea').value;
	var filmdata = "&name=" + title + "&info=" + info + "&thumblink=" + thumb + "&duration=" + duration + "&genres=" + genreStr;
	alert(filmdata);
	request.open("PUT", "http://localhost:8080/FilRougeHib/rest/films/" + filmid, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send(filmdata);
}

function supprimer_film(filmid) {
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200)
				afficher_message(request.responseText, "supprimé");
			charger_films();
		}
	}
	request.open("DELETE", "http://localhost:8080/FilRougeHib/rest/films/" + filmid, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.send();
}

function afficher_message(response, action) {
	var pMessage = document.getElementById('message_holder');
	var contenuJSON = JSON.parse(response);
	var contenu = "Le film '" + contenuJSON.titre + "' était " + action;
	pMessage.innerHTML = contenu;
}

function deleteGenreTag(genretag) {
	alert(genretag);
	const element = document.getElementById(genretag);
	element.remove();
}