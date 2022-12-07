package adventures;

import java.util.*;

class Puzzle202207 {
    class Files {
        private List<Files> files;

        public Files() {
            files = new ArrayList<>();
        }

        public String name;
        public boolean isFile;
        public long size;

        public void addFile(Files f) {
            files.add(f);
        }

        public Files parentDir;
    }

    public Map<String, Files> buildDirectoryStruct() {
        List<String> data = Helpers.loadFile("202207.txt");
        Files root = new Files();
        root.name = "root";
        root.isFile = false;
        Files currFile = root;
        Map<String, Files> fileStruct = new HashMap<>();
        fileStruct.put("root", root);
        for (String d : data) {
            if (d.contains("$ cd")) { // .. change directory
                if (d.equals("$ cd ..")) { // .. pop one directory above
                    currFile = currFile.parentDir;
                    continue;
                } else if (d.equals("$ cd /")) {
                    continue;
                } else {
                    String[] currFileName = d.split(" ");
                    currFile = fileStruct.get(currFile.name + "_" + currFileName[2]);
                }
            } else if (d.equals("$ ls")) { // .. list directory
                continue;
            } else {
                String[] currFileName = d.split(" ");
                Files newFile = new Files();
                newFile.name = currFile.name + "_" + currFileName[1];
                newFile.parentDir = currFile;
                if (currFileName[0].equals("dir")) {
                    newFile.isFile = false;
                } else {
                    newFile.isFile = true;
                    newFile.size = Long.parseLong(currFileName[0]);
                }
                fileStruct.put(newFile.name, newFile);
                currFile.addFile(newFile);
            }
        }
        return fileStruct;
    }

    public void part1() {
        Map<String, Files> fileStruct = buildDirectoryStruct();
        int totalSize = 0;
        for (Map.Entry<String, Files> e : fileStruct.entrySet()) {
            long size = calcSize(fileStruct, e.getKey());
            if (size < 100000 && !e.getValue().isFile) {
                totalSize += size;
            }
        }
        System.out.println(totalSize);
    }

    public long calcSize(Map<String, Files> map, String name) {
        long size = 0;
        if (map.get(name).isFile) {
            return map.get(name).size;
        } else {
            for (Files f : map.get(name).files) {
                size += calcSize(map, f.name);
            }
        }
        return size;
    }

    public void part2() {
        Map<String, Files> fileStruct = buildDirectoryStruct();
        long totalSpace = 70000000;
        long usedSpace = calcSize(fileStruct, "root");
        long unusedSpace = totalSpace - usedSpace;
        long updateRequiredSpace = 30000000;
        long neededSpace = updateRequiredSpace - unusedSpace;
        long minSize = Long.MAX_VALUE;
        for (Map.Entry<String, Files> e : fileStruct.entrySet()) {
            long size = calcSize(fileStruct, e.getKey());
            if (size > neededSpace) {
                minSize = Math.min(minSize, size);
            }
        }
        System.out.println(minSize);
    }
}
