define(['knockout', 'appController', 'ojs/ojmodule-element-utils', 'accUtils',
	'jquery'], function(ko, app, moduleUtils, accUtils, $) {

		class LoginViewModel {
			constructor() {
				
				
				
				var self = this;

				self.userName = ko.observable();
				self.pwd = ko.observable();
				self.message = ko.observable();
				self.error = ko.observable();

				self.googleUser = undefined;

				// Header Config
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
				})
				
					window.gapi.load('client:auth2', () => {
					gapi.auth2.init({
						clientId: '638815303419-2dg8r70sb4i5qt64s4crvsuu0n5m0ado.apps.googleusercontent.com',
						plugin_name: "juegos",
						scope: 'https://www.googleapis.com/auth/business.manage',
						immediate: true
					})

				});
			}

			login() {
				var self = this;
				var info;
				if (self.googleUser) {
					info = {
						name: self.googleUser.getBasicProfile().getName(),
						email: self.googleUser.getBasicProfile().getEmail(),
						id: self.googleUser.getBasicProfile().getId(),
					};
				}
				else {
					info = {
						name: this.userName(),
						pwd: this.pwd()
					};
				}

				var data = {
					data: JSON.stringify(info),
					url: "user/login",
					type: "post",
					contentType: 'application/json',
					success: function(response) {
						app.router.go({ path: "games" });
					},
					error: function(response) {
						self.error(response.responseJSON.message);
					}
				};
				$.ajax(data);
			}



			register() {
				app.router.go({ path: "register" });
			}


			connected() {
				accUtils.announce('Login page loaded.');
				document.title = 'Login';

				var self = this;
				let divGoogle = document.createElement('div');
				divGoogle.setAttribute('id', 'my-signin2');
				document.getElementById('accedeConGoogle').appendChild(divGoogle);
				gapi.signin2.render('my-signin2', {
					scope: 'profile email',
					width: 150,
					height: 50,
					longtitle: false,
					theme: 'dark',
					onsuccess: function(response) {
						self.googleUser = response;
						localStorage.login3rd = true;
						self.login();
					},
					onfailure: function(error) {
						console.log(error);
						self.googleUser = undefined;
					},
				});
			}

			disconnected() {
				// Implement if needed
			};

			transitionCompleted() {
				// Implement if needed
			};
		}

		return LoginViewModel;
	});

function onSignIn(googleUser) {

	var profile = googleUser.getBasicProfile();
	console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	console.log('Name: ' + profile.getName());

	console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
}


