import { defineConfig } from "cypress";
/*|| "https://vti-frontend.vercel.app"*/
export default defineConfig({
  e2e: {
    baseUrl: process.env["BASE_URL"] ,
    defaultCommandTimeout: 2000,
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },

  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
    },
    specPattern: "**/*.cy.ts",
  },
});
