
Pod::Spec.new do |s|
  s.name         = "RNIndoorManager"
  s.version      = "1.0.0"
  s.summary      = "RNIndoorManager"
  s.description  = <<-DESC
                  RNIndoorManager
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNIndoorManager.git", :tag => "master" }
  s.source_files  = "RNIndoorManager/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "IndoorAtlas"
  #s.dependency "others"

end

  