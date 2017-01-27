'use strict';

var path = require('path');
var gulp = require('gulp');
var paths = gulp.paths;

var browserSync = require('browser-sync');

function isOnlyChange(event) {
  return event.type === 'changed';
}

gulp.task('watch', function () {

  // gulp.watch([path.join(paths.src, '/*.html')]);

  gulp.watch(path.join(paths.src, '/app/**/*.html'), function(event) {
    browserSync.reload(event.path);
  });
});
