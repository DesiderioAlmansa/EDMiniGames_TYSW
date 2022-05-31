define(['knockout', 'appController', 'ojs/ojmodule-element-utils', 'accUtils',
	'jquery'], function(ko, app, moduleUtils, accUtils, $) {

		class MenuViewModel {
			constructor() {
				let self = this;

				//JUEGOS
				self.games = ko.observableArray([]);
				self.matches = ko.observableArray([]);

				//ESTADISTICAS
				self.statistics = ko.observableArray([]);
				self.visibleModal = ko.observable(false);


				self.x = ko.observable(null);
				self.y = ko.observable(null);


				//ERROR
				self.error = ko.observable(null);

				//CHAT
				self.chat = ko.observableArray([
					{
						user: "EDMiniGames",
						msg: "Bienvenido al chat de la partida :)",
					},
				]);

				self.inputChat = ko.observable(null);

				//HEADER			
				self.headerConfig = ko.observable({
					'view': [],
					'viewModel': null
				});
				moduleUtils.createView({
					'viewPath': 'views/header.html'
				}).then(function(view) {
					self.headerConfig({
						'view': view,
						'viewModel': app.getHeaderModel()
					})
				});

				self.jugarContraMaquina = false;
			}

			connected() {
				accUtils.announce('Juegos.');
				document.title = "Juegos";

				let self = this;

				self.getGames();
				self.getEstadisticas();
				self.connectChat();
			};


			getGames() {
				let self = this;
				let data = {
					type: "get",
					url: "/games/getGames",
					success: function(response) {
						self.games(response);
					},
					error: function(response) {
						console.error(response);
						self.error(response.responseJSON.message.toString());
					},
				};
				$.ajax(data);
			}


			getEstadisticas() {
				let self = this;

				let data = {
					type: "get",
					url: "/games/getEstadisticas",
					success: function(response) {
						self.statistics(response);
					},
					error: function(response) { },
				};
				$.ajax(data);
			}

			rendirse(matchId) {
				let self = this;
				let actualMatch = self.matches().find((match) => match.id == matchId);
				if (actualMatch.ready == false) {
					actualMatch.gameError =
						"No puedes eliminar una partida mientras estás esperando";
					this.actualizarPartida(matchId, actualMatch);
				} else {
					let data = {
						type: "get",
						url: "/games/rendirse/" + matchId,
						success: function(response) {
							self.reload(matchId);
						},
						error: function(response) {
							self.error(response.responseJSON.message.toString());
						},
					};
					$.ajax(data);
				}
			}

			openModal() {
				let self = this;
				self.visibleModal(true);
			}
			closeModal() {
				let self = this;
				self.visibleModal(false);
			}

			moverFichas(match) {
				let self = this;

				let info;

				info = {
					x: this.x(),
					y: this.y(),
					matchId: match.id,
				};
				
				let data;
				
				if(self.jugarContraMaquina){
					data = {
					type: "post",
					url: "/games//moveJuegoContraMaquina",
					data: JSON.stringify(info),
					contentType: "application/json",
					success: function(response) {
						console.log(JSON.stringify(response));
					},
					error: function(response) {
						console.error(response);
						self.error(response.responseJSON.message.toString());
						match.gameError = response.responseJSON.message;
						self.actualizarPartida(match.id, match);
					},
				};
				console.log(JSON.stringify(data));
				}else{
					data = {
					type: "post",
					url: "/games/move",
					data: JSON.stringify(info),
					contentType: "application/json",
					success: function(response) {
						console.log(JSON.stringify(response));
					},
					error: function(response) {
						console.error(response);
						self.error(response.responseJSON.message.toString());
						match.gameError = response.responseJSON.message;
						self.actualizarPartida(match.id, match);
					},
				};
				console.log(JSON.stringify(data));
				}
				 
				$.ajax(data);

			}


			actualizarPartida(matchId, newMatch) {
				let self = this;
				for (let i = 0; i < self.matches().length; i++)
					if (self.matches()[i].id == matchId) {
						self.matches.splice(i, 1);
						self.matches.splice(i, 0, newMatch);
						break;
					}
			}
			conectarWS() {
				let self = this;
				let ws = new WebSocket(`ws://${window.location.origin.split("//")[1]}/${"wsGenerico"}`);
				ws.onopen = function(event) {
					
				}
				ws.onmessage = function(event) {
					let msg = JSON.parse(event.data);
					if (msg.type == "PARTIDA_ACTUALIZADA") {
						let gameName = self
							.matches()
							.find((match) => match.id == msg.matchId).game;
						self.reload(msg.matchId);
					}
					if (msg.type == "PARTIDA_LISTA" || msg.type == "PARTIDA_FINALIZADA")
						self.reload(msg.matchId);
				};
			}

			joinGame(game) {
				let self = this;
				let data;

				if (self.jugarContraMaquina) {
					data = {
						type: "get",
						url: "/games/joinGameMaquina/" + game.name,
						success: function(response) {
							self.matches.push(response);
							self.conectarWS();
							console.log(JSON.stringify(response));
						},
						error: function(response) {
							console.error(response.responseJSON.message);
							self.error(response.responseJSON.message);
						}
					};
					
				}
				else {
					data = {
						type: "get",
						url: "/games/joinGame/" + game.name,
						success: function(response) {
							self.matches.push(response);
							self.conectarWS();
							console.log(JSON.stringify(response));
						},
						error: function(response) {
							console.error(response.responseJSON.message);
							self.error(response.responseJSON.message);
						}
					};

				}
				$.ajax(data);
			}

			joinJuegoMaquina(game) {
				let self = this;
				self.jugarContraMaquina = true;
				self.joinGame(game);
			}


			reload(matchId) {
				let self = this;

				let data = {
					type: "get",
					url: "/games/findMatch/" + matchId,
					success: function(response) {
						let actualMatch = self.matches().find((match) => match.id == matchId);
						let newMatch;

						newMatch = {
							...response,
							x: null,
							y: null,
							gameError: "",
						};

						self.actualizarPartida(matchId, newMatch);
					},
					error: function(response) {
						self.globalError(response.responseJSON.message);
					},
				};
				$.ajax(data);
			}


			connectChat() {

				let self = this;

				let ws = new WebSocket(`ws://${window.location.origin.split("//")[1]}/${"wsGenericoChat"}`);
				ws.onopen = function(event) { };
				ws.onmessage = function(event) {
					let msg = JSON.parse(event.data);
					self.addMsgChat(msg);
					self.handleScrollBottom();
				};
			}

			addMsgChat(msg) {
				let self = this;

				self.chat().push(msg);
				self.chat.valueHasMutated();
			}

			sendMessage() {
				let self = this;
				if (self.inputChat() != "" && !/^\s+$/.test(self.inputChat())) {
					let info = {
						msg: self.inputChat(),
					};

					let data = {
						type: "post",
						url: "/games/sendMessageChat",
						data: JSON.stringify(info),
						contentType: "application/json",
						success: function(response) {
							self.inputChat("");
						},
						error: function(response) { },
					};
					$.ajax(data);
				}
			}

			handleScrollBottom() {
				var objDiv = document.getElementById("chatContent");
				objDiv.scrollTop = objDiv.scrollHeight;
			}

			handleEnterChat(d, e) {
				let self = this;
				if (e.keyCode === 13) self.sendMessage();
			}



			disconnected() {
				// Implement if needed
			};

			transitionCompleted() {
				// Implement if needed
			};

			openModal() {
				let self = this;
				self.visibleModal(true);
			}
			closeModal() {
				let self = this;
				self.visibleModal(false);
			}
		}

		return MenuViewModel;
	});
