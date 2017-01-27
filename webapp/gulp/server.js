'use strict';

var gulp = require('gulp');

var paths = gulp.paths;

var browserSync = require('browser-sync');

function browserSyncInit(baseDir, files, browser) {
  browser = browser === undefined ? 'default' : browser;

  browserSync.instance = browserSync.init(files, {
    startPath: '/parse',
    server: {
      baseDir: '-',
      routes: {
        '/parse' : 'src/parse'
      },
      index: 'functionalsql.html', 
    },
    port: 3000,
    browser: browser
  });
}

gulp.task('serve', ['watch'], function () {
  browserSyncInit([
    paths.src
  ], [
    paths.src + '/parse/**/*.html'
  ]);

});

