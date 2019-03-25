import { AppPage } from './app.po';
import { browser, logging, element, protractor, by } from 'protractor';

describe('my-book-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
	browser.driver.manage().window().maximize();
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MyBooksUI');
  });

  it('should be redirected to /login route on opening the application', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.id('register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('user');
    browser.element(by.id('lastName')).sendKeys('Super lastUser');
    browser.element(by.id('userId')).sendKeys('users');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });
  

  it('should be able to login user and navigate to popular books', () => {
    browser.element(by.id('userId')).sendKeys('users');
    browser.element(by.id('password')).sendKeys('Super Userpass');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/books/popular');
  });

  it('should be able to search books', () => {
    browser.element(by.id('search')).click();
    expect(browser.getCurrentUrl()).toContain('/books/search');
    browser.element(by.id('search-button-input')).sendKeys('Super');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);
    const searchItems = element.all(by.id('book-title'));
    expect(searchItems.count()).toBeGreaterThan(0);
    for(let i = 0; i < 1; i += 1) {
      expect(searchItems.get(i).getText()).toContain('Getting a Good Job');
    }
  });

  it('should be able to add books to favorites', async() => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = element.all(by.css('.book-thumbnail'));
    expect(searchItems.count()).toBeGreaterThan(0);
    searchItems.get(0).click();
    browser.element(by.id('add-button')).click();
    browser.driver.sleep(10000);
  });
 
});

