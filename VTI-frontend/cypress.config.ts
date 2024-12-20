import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    baseUrl:"https://vti-frontend.vercel.app" ,
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
