/**
 * Created by barrie on 17/2/17.
 */
module.exports = function (grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            buildall: {
                options: {
                    mangle: true,
                    compress: {
                        drop_console: true
                    },
                    report: 'min'
                },
                files: [{
                    expand: true,
                    cwd: 'WebRoot/view',
                    src: '**/*.js',
                    dest: 'WebRoot/dist'
                }]
            }
        },
        concat: {
            options: {
                separator: ';'
            },
            build: {
                src: 'WebRoot/view/**/*.js',
                dest: 'dist/<%= pkg.name %>.js'
            }
        },
        watch: {
            scripts: {
                files: ['WebRoot/view/**/*.js'],
                tasks: 'default',
                options: {
                    spawn: true,
                    interrupt: true
                }
            }
        }
    });

    // 加载包含 "uglify" 任务的插件。
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-watch');

    // 默认被执行的任务列表。
    grunt.registerTask('default', ['uglify']);

};