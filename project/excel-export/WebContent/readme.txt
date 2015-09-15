
为保证导出的数据中文不为乱码，所有的模板，流都为utf8。

xlst或jxls是apache的poi的方式实现的，导出的文件是真正的excel。

velocity、httl不支持excel模板，需要把excel模板另存为htm的格式，指定的模板应为htm模板，导出的文件实际也是htm的，
只是显示的为excel，如果需要真正的excel，通过另存为excel即可。

velocity、httl的htm模板一定一定要为utf8的字符集，以支持中文字符，可通过记事本方式打开后修改，注意转码后的中文变为乱码。

velocity、httl的导出是先生成文件，在下载，注意制定策略删除生成的文件，本工程中是默认执行导出后立即删除。

注意模板的路径，jxls、velocity的模板放在WEB-INF/template目录下；httl放在classes的com/ist/httl/excel/目录下。